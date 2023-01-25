import { mount } from "@vue/test-utils";
import HomeComponent from "../../src/components/HomeComponent.vue";
import { createRouter, createWebHistory } from "vue-router";

let wrapper;

describe("App", () => {
  beforeEach(async () => {
    const router = createRouter({
      history: createWebHistory(),
      routes: [
        {
          path: "/game",
          name: "home",
          component: HomeComponent,
        },
      ],
    });
    router.push("/game?id=1&name=home");
    await router.isReady();

    wrapper = mount(HomeComponent, {
      global: {
        plugins: [router],
      },
    });
    await wrapper.vm.$nextTick();
  });

  test("test home component", () => {
    expect(wrapper.vm).toBeTruthy();
  });

  test("should update hoverValue with the passed value", () => {
    wrapper.vm.changeHoverValue({
      attributes: { title: { value: "Germany" } },
    });
    expect(wrapper.vm.$data.hoverValue).toBe("place your mouse over a country");
  });

  test("sets the selectedPaths data property to the provided value", () => {
    expect(wrapper.vm.selectedPaths).toEqual([]);
  });
  test("colorSet", () => {
    expect(
      wrapper.vm.colorSet({
        attributes: { owner: "p" },
      })
    ).toBe("black");
  });
  test("returns the neighbours of the territory with the specified name", () => {
    const continents = [
      {
        territory: [
          { name: "territory1", neighbours: ["territory2", "territory3"] },
          { name: "territory2", neighbours: ["territory1", "territory3"] },
        ],
      },
      {
        territory: [
          { name: "territory3", neighbours: ["territory1", "territory2"] },
        ],
      },
    ];
    const name = "territory1";
    expect(wrapper.vm.findNameTerritory(continents, name)).toEqual([
      "territory2",
      "territory3",
    ]);
  });

  it('returns "Territorio non trovato" if error status is 404', () => {
    const error = { response: { status: 404 } };
    expect(wrapper.vm.returnError(error)).toBe("Territorio non trovato");
  });

  it("returns error data if error status is not 404", () => {
    const error = { response: { status: 400, data: "Bad Request" } };
    expect(wrapper.vm.returnError(error)).toBe("Bad Request");
  });

  it('returns "Territorio non trovato" if error.response is not defined', () => {
    const error = { response: { status: 404, data: "Not Found" } };
    expect(wrapper.vm.returnError(error)).toBe("Territorio non trovato");
  });
  it("opens a new window with the correct parameters", async () => {
    const message = "Hello World";
    window.open = jest.fn();
    window.open.mockReturnValue({
      document: {
        body: {
          innerHTML: "",
        },
      },
    });

    await wrapper.vm.showMessage(message);
    expect(window.open).toHaveBeenCalledWith(
      "",
      "Message",
      "height=80,width=300"
    );
    expect(window.open().document.body.innerHTML).toBe(message);
  });
});
describe("changeHoverValue", () => {
  let allData = {
    map: {
      continents: [
        {
          territory: [
            { name: "country1", neighbours: ["neighbour1", "neighbour2"] },
            { name: "country2", neighbours: ["neighbour3", "neighbour4"] },
          ],
        },
      ],
    },
  };

  let mockFindNameTerritory = jest.fn();
  let findNameTerritory = jest.fn(() => ["neighbour1", "neighbour2"]);

  beforeEach(async () => {
    const router = createRouter({
      history: createWebHistory(),
      routes: [
        {
          path: "/game",
          name: "home",
          component: HomeComponent,
        },
      ],
    });
    router.push("/game?id=1&name=home");
    await router.isReady();
    wrapper = mount(HomeComponent, {
      global: {
        plugins: [router],
      },
    });
    jest
      .spyOn(global, "findNameTerritory")
      .mockImplementation(mockFindNameTerritory);
  });

  afterEach(() => {
    jest.clearAllMocks();
  });

  it("calls findNameTerritory with the correct arguments", () => {
    const value = { target: { attributes: { title: { value: "country1" } } } };
    changeHoverValue(value);
    expect(findNameTerritory).toHaveBeenCalledWith(
      allData.map.continents,
      "country1"
    );
  });
});
