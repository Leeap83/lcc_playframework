package services

import models._

import scala.util.Try

trait ProductService {

  def create(product: Product): Unit

  def update(product: Product): Try[Product]

  def findById(id: Long): Option[Product]

  def findAll(): List[Product]

}
