import { createStore } from "vuex"
import { mount } from "@vue/test-utils"
import App from "@/App.vue"
import NestedRoute from "@/components/NestedRoute.vue"
import routes from "@/routers.js"

const localVue = createStore({ state() { return { count: 0 } } })


describe("App", () => {
    it("renders a child component via routing", async () => {
        const wrapper = mount(App, {
            localVue,
            routes
        })

        routes.push("/nested-route")
        await wrapper.vm.$nextTick()

        expect(wrapper.findComponent(NestedRoute).exists()).toBe(false)
    })
})