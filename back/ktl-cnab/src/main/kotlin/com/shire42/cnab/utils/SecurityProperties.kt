package com.shire42.cnab.utils

import org.hibernate.validator.constraints.Length
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt-security")
class SecurityProperties {

    @Length(min = 42, message = "Minimum length for the secret is 42.")
    var secret = "Fe84mNU5ja6bk1AXX1kYQ84fT4HGIx4NFY0iaMVX2uFzgm9u2NPyVbNqOIONL1U5Kx-W81rwI2Kzqy5_5_3mPU_na42-fWOstZ5qVY87sFLSHtc9x55YPPuy-gcpjGeLAjqAzsD9a3LWLz9OpniwbAJJnQsVkEi5D4xomrRZwY-dw4VLK7l4mRXjpGyp6UkFxvKTYxOJ-1Pv9CiOC328af3T_Jn61F8mn0iUmn4G22pwUqCICGMMbf9pCEhvzjYtChmogFdjTdw2h1omgmRDndG1HCAKGbUBYsTRs4zUGLYCv2CeMSa7lwHkjv9EZBeuvj3HwkZi7rEjM_mTsamBQQ"
    var expirationTime: Int = 31 // in days
    var tokenPrefix = "Bearer "
    var headerString = "Authorization"
    var strength = 10

}