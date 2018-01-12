package com.homiak.andrii.todomvp

/**
 * Created by Andre on 06-Jan-18.
 */
interface BaseView<in T : BasePresenter> {
    fun setPresenter(presenter: T)
    fun isActive(): Boolean
}