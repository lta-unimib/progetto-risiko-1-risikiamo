import { shallowMount } from '@vue/test-utils';
import HomeComponent from '../../src/components/HomeComponent.vue';

describe('HomeComponent', () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(HomeComponent);
  });

  test('is a Vue instance', () => {
    expect(wrapper.isVueInstance).toBeTruthy();
  });

  test('initializes with correct data', () => {
    expect(wrapper.vm.zoom).toBe(1);
    expect(wrapper.vm.isDragging).toBe(false);
    expect(wrapper.vm.initialPosition).toEqual({ xi: 0, yi: 0 });
    expect(wrapper.vm.currentPosition).toEqual({ xc: 0, yc: 0 });
    expect(wrapper.vm.height).toBe(900);
    expect(wrapper.vm.width).toBe(1660);
    expect(wrapper.vm.x).toBe(0);
    expect(wrapper.vm.y).toBe(0);
    expect(wrapper.vm.diffx).toBe(0);
    expect(wrapper.vm.diffy).toBe(0);
    expect(wrapper.vm.selectedPaths).toEqual([]);
  });

  test('changes hoverValue when changeHoverValue is called', () => {
    wrapper.vm.changeHoverValue('Test Name');
    expect(wrapper.vm.hoverValue).toBe('Test Name');
  });

  test('sets selectedPaths when setSelectedPath is called', () => {
    wrapper.vm.setSelectedPath(['path1', 'path2']);
    expect(wrapper.vm.selectedPaths).toEqual(['path1', 'path2']);
  });

  test('opens trade window when openTradeWindow is called', () => {
    wrapper.vm.openTradeWindow();
    expect(wrapper.vm.tradeWindowOpen).toBe(true);
  });

  test('sets tradeImpossible to true when impossibleTrade is called', () => {
    wrapper.vm.impossibleTrade();
    expect(wrapper.vm.tradeImpossible).toBe(true);
  });

  test('closes trade window when closeTradeWindow is called', () => {
    wrapper.vm.closeTradeWindow();
    expect(wrapper.vm.tradeWindowOpen).toBe(false);
  });

  test('trades resources when trade is called', () => {
    wrapper.vm.trade();
    // You would need to add additional logic to test the actual resource trade
  });

  test('returns true when compareAdjacent is called with adjacent countries', () => {
    const path1 = { attributes: { title: { value: 'Country1' } } };
    const path2 = { attributes: { title: { value: 'Country2' } } };
    const result = wrapper.vm.compareAdjacent(path1, path2);
    expect(result).toBe(true);
  });
});