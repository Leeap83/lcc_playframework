package controllers

import javax.inject._
import play.api.mvc._

class ProductController @Inject()(
  val controllerComponents: ControllerComponents
) extends BaseController with play.api.i18n.I18nSupport{

  def init(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    //I am going to do stuff
    Ok(views.html.product.products())
  }


}
