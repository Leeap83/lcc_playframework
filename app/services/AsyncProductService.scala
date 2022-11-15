package services

import models._

import scala.concurrent.Future
import scala.util.Try

trait AsyncProductService {
  def create(product: Product): Unit

  def update(product: Product): Future[Try[Product]]

  def findById(id: Long): Future[Option[Product]]

  def findAll(): Future[List[Product]]
}
