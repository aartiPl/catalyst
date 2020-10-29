package net.igsoft.catalyst.database.datasource

object Datasource {
    fun mySql(): DatasourceBuilder {
        return DatasourceBuilder().apply {
            driverClassName = "com.mysql.cj.jdbc.Driver"
        }
    }
}
