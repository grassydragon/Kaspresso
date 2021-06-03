package com.kaspersky.kaspressample.web_tests

import android.Manifest
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.WebViewScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso_sample_core.MainActivity
import org.junit.Rule
import org.junit.Test

class WebViewTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() {

        before {
            activityTestRule.launchActivity(null)
        }.after {
        }.run {

            step("Open WebView Screen") {
                MainScreen {
                    webViewButton {
                        hasAnyText()
                        click()
                    }
                }
            }

            step("Find \"Sign in\" button and \"Protect your data\" title") {
                WebViewScreen {

                    webView {
                        withElement(
                            Locator.XPATH,
                            "/html/body/div[1]/section[1]/div/div/h2"
                        ) {
                            containsText("Protect your data")
                        }

                        withElement(
                            Locator.XPATH,
                            "/html/body/div[1]/header/section/div[3]/div[2]/button"
                        ) {
                            hasText("Sign in")
                        }
                    }
                }
            }

            step("Click \"Get Support\" button") {
                WebViewScreen {
                    webView {

                        withElement(
                            Locator.XPATH,
                            "/html/body/div[1]/section[5]/div/div/div[2]/div[3]/button"
                        ) {
                            compose(this@webView) {
                                or {
                                    containsText("fffuuuuu")
                                    hasText("fuuuu")
                                }
                                or {
                                    containsText("Ask questiop")
                                    hasText("Ask questio")
                                }
                                or {
                                    containsText("Ask question")
                                    hasText("Ask question")
                                }
                                or {
                                    containsText("Get Support")
                                    hasText("Get Support")
                                }
                            }
                        }

                        compose {
                            orWithElement(
                                Locator.XPATH,
                                "/html/body/div[1]/section[5]/div/div/div[2]/div[3]/button"
                            ) {
                                hasText("TRATATATA")
                            }
                            orWithElement(
                                Locator.XPATH,
                                "/html/body/div[1]/section[5]/div/div/div[2]/div[3]/button"
                            ) {
                                hasText("Get Support")
                                click()
                            }
                            orWithElement(
                                Locator.XPATH,
                                "//*[@id=\"app\"]/section[5]/div/div/div[2]/div[3]/button"
                            ) {
                                hasText("Ask question")
                                click()
                            }
                        }
                    }
                }
            }
        }
    }
}
