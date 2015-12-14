name := "MyHadoop"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "rediscala" at "http://dl.bintray.com/etaty/maven"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.3.10"

libraryDependencies += "com.etaty.rediscala" % "rediscala_2.10" % "1.4.0"

libraryDependencies += "org.mongodb" %% "casbah" % "2.8.1"

libraryDependencies += "org.apache.zookeeper" % "zookeeper" % "3.4.6"

libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "2.7.1"

libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.7.1"

libraryDependencies += "org.apache.hbase" % "hbase-client" % "1.1.1"

libraryDependencies += "org.apache.hbase" % "hbase" % "1.1.1"

libraryDependencies += "org.apache.hbase" % "hbase-common" % "1.1.1"

libraryDependencies += "com.gravity" % "gravity-hpaste" % "0.1.11"

libraryDependencies += "com.twitter" % "util-core_2.10" % "6.27.0"

libraryDependencies += "com.twitter" % "util-zk_2.10" % "6.27.0"

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.8"

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-mapping" % "2.1.8"

libraryDependencies += "org.apache.thrift" % "libthrift" % "0.9.3"

libraryDependencies += "org.apache.cassandra" % "cassandra-thrift" % "2.2.3"
