package avans.avd.rent_my_car_android_app.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState (val usernameError: Int? = null,
                      val passwordError: Int? = null,
                      val isDataValid: Boolean = false)