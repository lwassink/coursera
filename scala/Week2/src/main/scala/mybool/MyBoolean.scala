package mybool

/**
  * Created by lwassink on 3/8/17.
  */
abstract  class MyBoolean {
  def ifThenElse[T](t: T, e: T): T

  def || (other: MyBoolean): MyBoolean = ifThenElse(myTrue, other)
  def && (other: MyBoolean): MyBoolean = ifThenElse(other, myFalse)
  def unary_!(): MyBoolean             = ifThenElse(myFalse, myTrue)
  def == (other: MyBoolean): MyBoolean = ifThenElse(other, !other)
  def != (other: MyBoolean): MyBoolean = ifThenElse(!other, other)
  def < (other: MyBoolean): MyBoolean  = ifThenElse(myFalse, other)
}

object myTrue extends MyBoolean {
  def ifThenElse[T](t: T, e: T): T = t
}

object myFalse extends MyBoolean {
  def ifThenElse[T](t: T, e: T): T = e
}