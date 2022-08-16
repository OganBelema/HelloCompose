package com.oganbelema.hellocompose

data class Portfolio (var name: String, var description: String) {
    companion object {
        fun portfolioList(): List<Portfolio> {
            return listOf(
                Portfolio("Customer Support Cloud App",
                    "A web application built with AWS to enable customer support personnel to translate complaint text."),
                Portfolio("Code Offloading Incentive",
                    "A blockchain-based incentive for task offloading to volunteered mobile devices."),
                Portfolio("Todo App", "A todo application built with django and deployed to using Jenkins pipeline")
            )
        }
    }
}