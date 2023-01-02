
import { mount } from '@vue/test-utils'
import HomeComponent from '../../src/components/HomeComponent.vue'

describe('select italy', () => {

  const wrapper = mount(HomeComponent)
  console.log(wrapper)

  test('does a wrapper exist', () => {
    expect(wrapper.exists()).toBe(true)
  })

  test('does change over value function', () => {
    wrapper.vm.changeHoverValue('{"mouseEvent": {"target": "Italy"}}')
    expect(wrapper.vm.hoverValue).toBe('Italy')
  })


  test('does change over value function 2', () => {
    wrapper.vm.changeHoverValue('France')
    expect(wrapper.vm.hoverValue).toBe('place your mouse over a country')
  })
})