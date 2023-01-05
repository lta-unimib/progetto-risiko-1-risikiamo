
import { mount } from '@vue/test-utils'
import HomeComponent from '../../src/components/HomeComponent.vue'

describe('select italy', () => {

  const wrapper = mount(HomeComponent)
  const hoverValue = wrapper.vm.hoverValue
  console.log(hoverValue)

  test('does a wrapper exist', () => {
    expect(wrapper.exists()).toBe(true)
  })

  test('does change over value function', () => {
    wrapper.vm.changeHoverValue({ target: { attributes: { title: "Italy" } } })
    expect(hoverValue).toBe('place your mouse over a country')
  })


  test('does change over value function 2', () => {
    wrapper.vm.changeHoverValue('France')
    expect(hoverValue).toBe('place your mouse over a country')
  })

})