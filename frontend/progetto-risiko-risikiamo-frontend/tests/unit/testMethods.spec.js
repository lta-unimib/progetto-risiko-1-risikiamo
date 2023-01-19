import { shallowMount } from '@vue/test-utils';
import HomeComponent from '../../src/components/HomeComponent.vue';

describe('HomeComponent', () => {

  test('zoomIn method increases zoom by 0.5', () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.vm.zoom = 1;
    wrapper.vm.zoomIn();
    expect(wrapper.vm.zoom).toBe(1.5);
  });

  test('zoomOut method decreases zoom by 0.5', () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.vm.zoom = 2;
    wrapper.vm.zoomOut();
    expect(wrapper.vm.zoom).toBe(1.5);
  });

  test('compareAdjacent method correctly identifies if two countries are adjacent', () => {
    const wrapper = shallowMount(HomeComponent);
    let path1 = { attributes: { title: { value: 'Germany' } } };
    let path2 = { attributes: { title: { value: 'France' } } };
    expect(wrapper.vm.compareAdjacent(path1, path2)).toBe(true);
  });

  test('onMouseDown method sets isDragging to true and assigns current mouse coordinates to initialPosition', () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.vm.onMouseDown({ clientX: 50, clientY: 100 });
    expect(wrapper.vm.isDragging).toBe(true);
    expect(wrapper.vm.initialPosition).toEqual({ xi: 50, yi: 100 });
  });

  test('onMouseUp method sets isDragging to false and assigns current x and y to diffx and diffy', () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.setData({ isDragging: true, x: 20, y: 40 });
    wrapper.vm.onMouseUp();
    expect(wrapper.vm.isDragging).toBe(false);
    expect(wrapper.vm.diffx).toBe(20);
    expect(wrapper.vm.diffy).toBe(40);
  });

  test('changeHoverValue method sets the hoverValue property to the title of the hovered path', () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.vm.changeHoverValue({ target: { attributes: { title: { value: 'Germany' } } } });
    expect(wrapper.vm.hoverValue).toBe('Germany');
  });

});