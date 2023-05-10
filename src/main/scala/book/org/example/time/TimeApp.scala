package book.org.example.time

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.blaze.server.BlazeServerBuilder

import scala.concurrent.ExecutionContext


object TimeApp extends IOApp {

  // run TimeApp and try:
  // `curl -i http://0.0.0.0:8000/datetime/italy`
  // HTTP/1.1 200 OK ... Wed, 10 May 2023 20:37:48 +0200
  // `curl -i http://0.0.0.0:8000/datetime/invalid`
  // HTTP/1.1 404 Not Found ... Unknown timezone for country invalid

  private val httpApp = Router(
    "/" -> new TimeApi().routes
  ).orNotFound

  override def run(args: List[String]): IO[ExitCode] = stream(args).compile.drain.as(ExitCode.Success)

  private def stream(args: List[String]) =
    BlazeServerBuilder[IO](ExecutionContext.global)
      .bindHttp(8000, "0.0.0.0")
      .withHttpApp(httpApp)
      .serve

}
