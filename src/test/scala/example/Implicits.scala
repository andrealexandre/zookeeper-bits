package example

import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}

object Implicits {

  implicit class FutureWait[A](future: Future[A]) {
    def await(atMost: Duration = 2.seconds): A = Await.result(future, atMost = atMost)
  }

}
