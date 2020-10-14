package net.igsoft.catalyst.database.datasource

import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

class DatasourceBuilder {


    fun build(): DataSource {
        return HikariDataSource()
    }
}
