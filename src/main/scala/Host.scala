package lvfpug.contextbounds

case class Host(hostname: String, ip: String) {
  def ping = println(s"$hostname [$ip]: pong")
  override def toString = hostname
}
