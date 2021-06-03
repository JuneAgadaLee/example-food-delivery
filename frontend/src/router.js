
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ClaimManager from "./components/ClaimManager"

import ClaimPage from "./components/ClaimPage"
import ReviewManager from "./components/ReviewManager"

import PaymentManager from "./components/PaymentManager"

import ClaimHistoryManager from "./components/ClaimHistoryManager"

import ProgressPage from "./components/ProgressPage"
export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/Claim',
                name: 'ClaimManager',
                component: ClaimManager
            },

            {
                path: '/ClaimPage',
                name: 'ClaimPage',
                component: ClaimPage
            },
            {
                path: '/Review',
                name: 'ReviewManager',
                component: ReviewManager
            },

            {
                path: '/Payment',
                name: 'PaymentManager',
                component: PaymentManager
            },

            {
                path: '/ClaimHistory',
                name: 'ClaimHistoryManager',
                component: ClaimHistoryManager
            },

            {
                path: '/ProgressPage',
                name: 'ProgressPage',
                component: ProgressPage
            },


    ]
})
