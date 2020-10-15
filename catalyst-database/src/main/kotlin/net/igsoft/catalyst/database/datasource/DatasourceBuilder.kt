package net.igsoft.catalyst.database.datasource

import com.zaxxer.hikari.HikariDataSource
import mu.KotlinLogging
import java.util.concurrent.TimeUnit
import javax.sql.DataSource

private val LOGGER = KotlinLogging.logger {}

class DatasourceBuilder {
    var jdbcUrl = ""
    var driverClassName = ""
    var username = ""
    var password = ""
    var connectionInitSql = "SELECT 1"
    var connectionTimeout = TimeUnit.MINUTES.toMillis(1)
    var maxLifetime = TimeUnit.MINUTES.toMillis(15)
    var validationTimeout = TimeUnit.MINUTES.toMillis(5)
    var socketConnectTimeout = TimeUnit.MINUTES.toMillis(15)
    var socketTimeout = TimeUnit.MINUTES.toMillis(60)


    fun build(): DataSource {
        require(jdbcUrl.isNotBlank())
        require(driverClassName.isNotBlank())
        require(username.isNotBlank())
        require(password.isNotBlank())

        printDatasourceProperties()

        val hikariDataSource = HikariDataSource()
        hikariDataSource.jdbcUrl = jdbcUrl
        hikariDataSource.driverClassName = driverClassName
        hikariDataSource.username = username
        hikariDataSource.password = password
        hikariDataSource.connectionInitSql = connectionInitSql
        hikariDataSource.connectionTimeout = connectionTimeout
        hikariDataSource.maxLifetime = maxLifetime
        hikariDataSource.validationTimeout = validationTimeout

        //Timeout for socket connect; 0 - no timeout
        hikariDataSource.addDataSourceProperty("connectTimeout", socketConnectTimeout)
        //Timeout on network socket; 0 - no timeout
        hikariDataSource.addDataSourceProperty("socketTimeout", socketTimeout)
        hikariDataSource.addDataSourceProperty("useCursorFetch", true)
        hikariDataSource.addDataSourceProperty("failOverReadOnly", false)

        return hikariDataSource
    }

    private fun printDatasourceProperties(): DatasourceBuilder {
        LOGGER.info("Datasource properties")
        return this
    }
}
