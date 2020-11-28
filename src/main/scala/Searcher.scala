import scala.collection.SortedSet

object Searcher {
	def first(term: String): Option[(Int, Int)] = {
		Store.positionIndices.get(term).map { postingList =>
			(postingList.head._1, postingList.head._2.head)
		}
	}

	def last(term: String): Option[(Int, Int)] = {
		Store.positionIndices.get(term).map { postingList =>
			(postingList.last._1, postingList.last._2.last)
		}
	}

	def next(term: String, position: (Int, Int)): Option[(Int, Int)] = {
		last(term) match {
			case None => None
			case Some((a: Int, b: Int)) if a < position._1 && b < position._2 => None
			case _ =>
				Store.positionIndices.get(term).flatMap { postingList: SortedSet[(Int, SortedSet[Int])] =>
					postingList.find(_._1 == position._1).flatMap { positions: (Int, SortedSet[Int]) =>
						positions._2.iteratorFrom(position._2 + 1).nextOption.fold(next(term, (position._1 + 1, 0))) { nextPosition =>
							Some((positions._1, nextPosition))
						}
					}
				}
		}
	}
}
