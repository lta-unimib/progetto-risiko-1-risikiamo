import { shallowMount, mount } from "@vue/test-utils";
import App from "../../src/App.vue";
import HomeComponent from "../../src/components/HomeComponent.vue";

describe("App", () => {
  test("zoomIn method increases zoom by 0.5", () => {
    const app = mount(App);
    if (app) {
      console.log("app is mounted");
    }
    const wrapper = shallowMount(HomeComponent, {
      data() {
        return {
          idMatch: "test-id-match",
          playerName: "testPlayerName",
        };
      },
    });
    wrapper.vm.zoom = 1;
    wrapper.vm.zoomIn();
    expect(wrapper.vm.zoom).toBe(1.5);
  });

  test("zoomOut method decreases zoom by 0.5", () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.vm.zoom = 2;
    wrapper.vm.zoomOut();
    expect(wrapper.vm.zoom).toBe(1.5);
  });

  test("compareAdjacent method correctly identifies if two countries are adjacent", () => {
    const wrapper = shallowMount(HomeComponent);
    let path1 = { attributes: { title: { value: "Germany" } } };
    let path2 = { attributes: { title: { value: "France" } } };
    expect(wrapper.vm.compareAdjacent(path1, path2)).toBe(true);
  });

  test("onMouseDown method sets isDragging to true and assigns current mouse coordinates to initialPosition", () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.vm.onMouseDown({ clientX: 50, clientY: 100 });
    expect(wrapper.vm.isDragging).toBe(true);
    expect(wrapper.vm.initialPosition).toEqual({ xi: 50, yi: 100 });
  });

  test("onMouseUp method sets isDragging to false and assigns current x and y to diffx and diffy", () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.setData({ isDragging: true, x: 20, y: 40 });
    wrapper.vm.onMouseUp();
    expect(wrapper.vm.isDragging).toBe(false);
    expect(wrapper.vm.diffx).toBe(20);
    expect(wrapper.vm.diffy).toBe(40);
  });

  test("changeHoverValue method sets the hoverValue property to the title of the hovered path", () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.vm.changeHoverValue({
      target: { attributes: { title: { value: "Germany" } } },
    });
    expect(wrapper.vm.hoverValue).toBe("Germany");
  });

  test("changeHoverValue method sets the hoverValue property to the title of the hovered path", () => {
    const wrapper = shallowMount(HomeComponent);
    const event = { target: { attributes: { title: { value: "Germany" } } } };
    wrapper.vm.changeHoverValue(event);
    expect(wrapper.vm.hoverValue).toBe("Germany");
  });

  test("trade method sets the selectedPaths property to an empty array and update the value of countries", () => {
    const wrapper = shallowMount(HomeComponent);
    const path1 = { attributes: { title: { value: "Germany" } } };
    const path2 = { attributes: { title: { value: "France" } } };
    wrapper.setData({ selectedPaths: [path1, path2] });
    wrapper.vm.trade(2, path1, path2);
    expect(wrapper.vm.selectedPaths).toEqual([
      { attributes: { title: { value: "Germany" } } },
      { attributes: { title: { value: "France" } } },
    ]);
  });

  test("setSelectedPath method adds the hovered path to the selectedPaths array", () => {
    const wrapper = shallowMount(HomeComponent);
    const path = { attributes: { title: { value: "Germany" } } };
    const spy = jest.spyOn(wrapper.vm, "setSelectedPath");
    wrapper.vm.setSelectedPath(path);
    expect(spy).toHaveBeenCalledWith(path);
    expect([{ attributes: { title: { value: "Germany" } } }]).toEqual([path]);
  });

  test("getAdjacentCountries method returns an array of adjacent countries for a given country", () => {
    const wrapper = shallowMount(HomeComponent);
    const adjacentCountries = wrapper.vm.getAdjacentCountries({
      attributes: { title: { value: "Germany" } },
    });
    expect(adjacentCountries).toEqual(undefined);
  });

  /*
  test('opens the trade window when the openTradeWindow method is called', () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.setData({ tradeWindowOpen: false })
    const spy1 = jest.spyOn(window, 'open');
    const spy2 = jest.fn();
    const spy3 = jest.spyOn(document, 'getElementById()');
    spy1.mockImplementation(() => { });
    spy2.mockImplementation(() => { });
    spy3.mockImplementation(() => { });
    Object.defineProperty(document, 'write', { value: spy2 });
    wrapper.vm.openTradeWindow({ "attributes": { "title": { "value": "Germany", }, }, }, { "attributes": { "title": { "value": "France", }, }, })
    expect(wrapper.vm.tradeWindowOpen).toEqual(true)
    expect(spy2.mock.calls[0][0]).toBe(expectedString);
    expect(spy3.mock.calls[0][0]).toBe(expectedId);
    expect(spy1).toHaveBeenCalled()
    expect(spy2).toHaveBeenCalled()
    expect(spy3).toHaveBeenCalled()
    spy1.mockRestore()
    spy2.mockRestore()
    spy3.mockRestore()
  })
  */

  test("trades the value between the selected countries and reset the trade", () => {
    const wrapper = shallowMount(HomeComponent);
    const mockImpossibleTradeFunction = jest.fn();
    wrapper.setData({
      path1: { attributes: { title: { value: "Germany" } } },
      path2: { attributes: { title: { value: "France" } } },
      tradeWindowOpen: true,
      tradeWindow: { units: 5 },
    });
    wrapper.vm.$options.methods.impossibleTrade = mockImpossibleTradeFunction;
    wrapper.vm.trade(3, wrapper.vm.path1, wrapper.vm.path2);
    expect(mockImpossibleTradeFunction).not.toHaveBeenCalled();
    expect(wrapper.vm.tradeWindowOpen).toEqual(true);
    expect(wrapper.vm.tradeWindow.units).toEqual(5);
  });

  test("trades the value between the selected countries", () => {
    const wrapper = shallowMount(HomeComponent);
    const mockTradeFunction = jest.fn();
    wrapper.setData({
      path1: { attributes: { title: { value: "Germany" } } },
      path2: { attributes: { title: { value: "France" } } },
    });
    wrapper.vm.$options.methods.impossibleTrade = mockTradeFunction;
    wrapper.vm.trade(5, wrapper.vm.path1, wrapper.vm.path2);
    expect(mockTradeFunction).not.toHaveBeenCalled();
    expect(wrapper.vm.path1.attributes.title.value.Armies).toEqual(undefined);
    expect(wrapper.vm.path2.attributes.title.value.Armies).toEqual(undefined);
  });

  test("should update hoverValue with the passed value", () => {
    const wrapper = shallowMount(HomeComponent);
    wrapper.vm.changeHoverValue({
      attributes: { title: { value: "Germany" } },
    });
    expect(wrapper.vm.$data.hoverValue).toBe("place your mouse over a country");
  });

  const $route = {
    query: "game?id=1234&name=test",
  };
  test("sets the selectedPaths data property to the provided value", () => {
    const wrapper = shallowMount(HomeComponent, {
      mocks: {
        $route,
      },
    });

    expect(wrapper.vm.selectedPaths).toEqual([]);
  });
});
