package controllers

import play.api.mvc._

import javax.inject._

class ContactController @Inject()(
  val controllerComponents: ControllerComponents
) extends BaseController with play.api.i18n.I18nSupport{

  def init(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    //I am going to do stuff
    Ok(views.html.contact.contact())
  }
}
