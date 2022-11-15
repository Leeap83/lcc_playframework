package services

import models._
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.{Document, MongoCollection, MongoDatabase}

import javax.inject.Inject
import scala.concurrent.Future
import scala.util.Try

class MongoProductService @Inject()(mongoCollection: MongoDatabase) extends AsyncProductService {

  val productCollection: MongoCollection[Document] = mongoCollection.getCollection("products")

  override def create(product: models.Product): Unit = {
    val document: Document = productToDocument(product)
    productCollection
      .insertOne(document)
      .subscribe(
        r => println(s"Successful Insert $r"),
        t => t.printStackTrace(),
        () => "Insert Complete"
      )
  }


  override def findById(id: Long): Future[Option[Product]] = {
    productCollection
      .find(equal("_id", id))
      .map { d =>
        documentToProduct(d)
      }
      .toSingle()
      .headOption()
  }

  private def productToDocument(product: Product): Document = {
    Document(
      "_id" -> product.id,
      "name" -> product.name,
      "description" -> product.description,
      "price" -> product.price,
      "imageUrl" -> product.imageUrl,
    )
  }

  private def documentToProduct(d: Document) = {
    Product(
      d.getLong("_id"),
      d.getString("name"),
      d.getString("description"),
      d.getString("price"),
      d.getString("imageUrl"),
    )
  }
  override def update(product: models.Product): Future[Try[Product]] = ???

  override def findAll(): Future[List[Product]] = ???
}
