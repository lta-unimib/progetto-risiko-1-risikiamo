<script>
import axios from 'axios';
import { ref } from 'vue';
//import UserComponent from './UserComponent.vue';
export default {

    name: "HomeComponent",
    components: {
        //CardComponent: () => import('./CardComponent.vue'),
    },
    data() {
        return {
            gameStarted: false,
            idMatch: this.$route.query.id,
            playerName: this.$route.query.name,
            reinforcement: 0,
            svg: null,
            cards: [],
            continents: [],
            player: [],
            currentPlayer: null,
            players: [],
            hoverValue: name,
            zoom: 1,
            isDragging: false,
            initialPosition: { xi: 0, yi: 0 },
            currentPosition: { xc: 0, yc: 0 },
            height: 900,
            width: 1660,
            x: 0,
            y: 0,
            diffx: 0,
            diffy: 0,
            selectedPaths: [],
            winner: null,
            paths: [],
            pathsNode: [],
        }

    },

    methods: {
        getAdjacentCountries: getAdjacentCountries,
        changeHoverValue: changeHoverValue,
        setSelectedPath: setSelectedPath,
        openTradeWindow: openTradeWindow,
        impossibleTrade: impossibleTrade,
        closeTradeWindow: closeTradeWindow,
        openPlaceWindow: openPlaceWindow,
        closePlaceWindow: closePlaceWindow,
        actionWindow: actionWindow,
        closeActionWindow: closeActionWindow,
        trade: trade,
        recoverPlayer() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    for (let i = 0; i < response.data.players.length; i++) {
                        if (response.data.players[i].name == this.playerName) {
                            this.player = response.data.players[i];
                            console.log(this.player);
                        } else {
                            console.log("error");
                        }
                    }
                })
                .catch(error => {
                    console.log(error);
                });
        },
        place(value, path) {
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/place?owner=' + path.attributes.title.value + '&army=' + value)
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                });
        },
        startMatch() {
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/start')
                .then(response => {
                    console.log(response.data);
                    this.gameStarted = true;
                })
                .catch(error => {
                    console.log(error);
                });
        },
        setPOV() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    console.log(response.data);
                    this.currentPlayer = response.data.currentPlayer;
                    console.log(this.currentPlayer);
                })
                .catch(error => {
                    console.log(error);
                });
            if (this.playerName != this.currentPlayer) {
                console.log("not your turn");
                console.log(this.paths);
                for (let i = 0; i < this.paths.length; i++) {
                    this.paths[i].addEventListener("mouseover", this.changeHoverValue);
                }
            } else {
                console.log("your turn");
            }
        },
        printid() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                });
            this.recoverPlayer();
        },
        compareAdjacent(path1, path2) {
            const adjacencyList = require('../assets/dataStates.json').map.continents.territories;
            const country1 = path1.attributes.title.value;
            const country2 = path2.attributes.title.value;
            //console.log(adjacencyList[country1]);
            if (adjacencyList[country1].neighbours && adjacencyList[country1].neighbours.includes(country2)) {
                return true;
            } else {
                return false;
            }
        },
        zoomIn() {
            if (this.zoom < 2.5) {
                this.zoom += 0.5;
                this.height = this.height * this.zoom;
                this.width = this.width * this.zoom;
            }
            else {
                this.zoom = 2.5;
                this.height = this.height * this.zoom;
                this.width = this.width * this.zoom;
                //console.log("zoom in impossibile sei già a 2.5");
            }

        },
        zoomOut() {

            if (this.zoom >= 1.5) {
                this.zoom -= 0.5;
                this.height = this.height * this.zoom;
                this.width = this.width * this.zoom;
            }
            else {
                this.zoom = 1;
                this.height = this.height * this.zoom;
                this.width = this.width * this.zoom;
                ////console.log("zoom out impossibile sei già a 1");
            }

        },
        onMouseDown(event) {
            this.isDragging = true;
            this.initialPosition = {
                xi: event.clientX,
                yi: event.clientY
            };
        },
        onMouseUp() {
            this.isDragging = false;
            this.diffx = this.x;
            this.diffy = this.y;
        },
        onMouseMove(event) {
            if (this.$refs.svg) {
                let svg = this.$refs.svg;
                if (this.isDragging) {
                    this.currentPosition = {
                        xc: event.clientX,
                        yc: event.clientY
                    };

                    let diffx2 = this.currentPosition.xc - this.initialPosition.xi;
                    let diffy2 = this.currentPosition.yc - this.initialPosition.yi;
                    diffx2 = diffx2 + this.diffx;
                    diffy2 = diffy2 + this.diffy;
                    this.x = diffx2;
                    this.y = diffy2;

                    if (diffx2 < -(this.width / 2))
                        diffx2 = -(this.width / 2);
                    if (diffy2 < -(this.height / 2))
                        diffy2 = -(this.height / 2);

                    svg.style.transform = `translate(${diffx2}px, ${diffy2}px)`;

                }
            }

        },
        getMap() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/svg')
                .then(response => {
                    console.log(response.data);
                    this.svg = response.data;
                    console.log(this.svg);
                })
                .catch(error => {
                    console.log(error);
                });
        },
        getCards() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/')
                .then(response => {
                    console.log(response.data);
                    this.cards = response.data.cards;
                })
                .catch(error => {
                    console.log(error);
                });

        },
        getContinents() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    this.continents = response.data.map.continents;
                    console.log(this.continents);
                })
                .catch(error => {
                    console.log(error);
                });

        },
        surrend() {
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/surrend')
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                });
        },
        skip() {
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/skip')
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                });
        },
        attack(value, path1, path2) {
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/attack?owner=' + path1.attributes.title.value + '&army=' + value + '&neighbor=' + path2.attributes.title.value)
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                });
        },
        move(value, path1, path2) {
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/move?owner=' + path1.attributes.title.value + '&army=' + value + '&neighbor=' + path2.attributes.title.value)
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                });
        },
        getWinner() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    console.log(response.data.winner);
                    this.winner = response.data.winner;
                })
                .catch(error => {
                    console.log(error);
                });
        },
        getReinforcement() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/')
                .then(response => {
                    console.log(response.data.reinforcement);
                    this.reinforcement = response.data.reinforcement;
                })
                .catch(error => {
                    console.log(error);
                });
        }
    },

    mounted() {
        this.getMap();
        // window.addEventListener("click", this.setSelectedPath);
        // window.addEventListener("click", (event) => {
        //     const path = event.target;
        //     this.selectedPaths.push(path);
        //     if (this.selectedPaths.length === 2) {
        //         this.selectedPaths[0].classList.remove("SelectedPath");
        //         this.selectedPaths[0].classList.add("pathFrom");
        //         this.selectedPaths[1].classList.remove("SelectedPath");
        //         this.selectedPaths[1].classList.add("pathTo");
        //         for (let i = 0; i < paths.length; i++) {
        //             paths[i].classList.remove("AdjacentPath");
        //         }
        //         this.selectedPaths[1].classList.add("AdjacentPath");
        //         if (this.compareAdjacent(this.selectedPaths[0], this.selectedPaths[1])) { //&& this.selectedPaths[0].Armies > 1 when modded map
        //             this.openTradeWindow(this.selectedPaths[0], this.selectedPaths[1]);
        //         } else {
        //             this.impossibleTrade();
        //             this.selectedPaths[0].classList.remove("pathFrom");
        //             this.selectedPaths[1].classList.remove("pathTo");
        //             this.selectedPaths[1].classList.remove("AdjacentPath");
        //             ////console.log("not okè");
        //         }

        //         this.selectedPaths = [];
        //     }
        // });
    },

    created() {

        setInterval(() => {
            this.setPOV();

        }, 5000);

        setInterval(() => {
            this.getCards();
        }, 5000);

        setInterval(() => {
            this.getContinents();
        }, 5000);

        setInterval(() => {
            this.getReinforcement();
        }, 3000);

        setInterval(() => {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    this.gameStarted = response.data.gameStarted;
                    console.log(this.gameStarted);
                })
                .catch(error => {
                    console.log(error);
                });
            if (this.gameStarted === false) {
                console.log("game not started");

            }
            else {
                console.log("game started");
                this.paths = document.querySelectorAll("path");
                for (let i = 0; i < this.paths.length; i++) {
                    this.paths[i].addEventListener("mouseover", this.changeHoverValue);
                }
            }
        }, 3000);
        // window.addEventListener("click", (event) => {
        //     if (this.gameStarted === false) {
        //         console.log("game not started");
        //     }
        //     else {
        //         const path = event.target;
        //         this.selectedPaths.push(path);
        //         path.classList.remove("SelectedPath");
        //         path.classList.add("pathFrom");
        //         for (let j = 0; j < this.paths.length; j++) {
        //             let item = {
        //                 name: this.paths[j].attributes.name,
        //                 army: this.paths[j].attributes.army,
        //                 owner: this.paths[j].attributes.owner,
        //             }
        //             this.pathsNode.push(item);
        //             console.log(this.pathsNode);
        //         }
        //         for (let i = 0; i < this.paths.length; i++) {
        //             this.paths[i].classList.remove("AdjacentPath");
        //         }
        //         this.actionWindow(path);
        //     }

        // }
        // );
    }
}


const name = ref("place your mouse over a country");


function impossibleTrade() {
    const instructions = "you can't move troops from the first country you click to the second one you click.";
    const errorWindow = window.open('', 'Trade Instructions', 'height=300,width=600');
    errorWindow.document.write(instructions);
    setTimeout(() => {
        errorWindow.window.close();
        //console.log("closed");
    }, 5000);
}

function actionWindow(path) {
    const instructions = '<p>scegli se posizionare i rinforzi (obbligatorio se non si sono posizionati tutti), attaccare o spostarsi</p><div><input type="radio" id="place" name="action" value="place"><label for="place">Piazza</label></div><div><input type="radio" id="attack" name="action" value="attack"><label for="attack">Attacca</label></div><div><input type="radio" id="move" name="action" value="move"><label for="move">Sposta</label></div><p>Inserisci nel caso di spostamento o attacco il territorio di destinazione</p><input type="text" id="destination"><p>inserisci il numero di truppe da spostare o attaccare o rifornire</p><input type="number" id="number" min="1" ><button id="doAction">ok</button><button id="exit">close</button>';
    const actionWindow = window.open('', 'Trade Instructions', 'height=300,width=600');
    actionWindow.document.write(instructions);
    const actionButton = actionWindow.document.getElementById('doAction');
    if (actionWindow.document.getElementById('place').checked) {
        actionButton.addEventListener('click', () => this.place(actionWindow.document.getElementById('number').value, path));
        console.log("place");
    }
    else if (actionWindow.document.getElementById('attack').checked) {
        actionButton.addEventListener('click', () => this.attack(actionWindow.document.getElementById('number').value, path, actionWindow.document.getElementById('destination').value));
        console.log("attack");
    }
    else if (actionWindow.document.getElementById('move').checked) {
        actionButton.addEventListener('click', () => this.move(actionWindow.document.getElementById('number').value, path, actionWindow.document.getElementById('destination').value));
        console.log("move");
    }
    const exitButton = actionWindow.document.getElementById('exit');
    exitButton.addEventListener('click', () => this.closeActionWindow(path, actionWindow));

}

function closeActionWindow(path, actionWindow) {
    actionWindow.window.close();
    path.classList.remove("pathFrom");
    path.classList.remove("pathTo");
    path.classList.remove("AdjacentPath");
    path.classList.remove("SelectedPath");
}


function openPlaceWindow(path) {
    const instructions = '<p>you can place from 1 to every reinforcement you have on the territory you choose.</p><input type="number" id="number" min="1" ><button id="button">place</button><button id="exit">close</button>';
    const placeWindow = window.open('', 'Trade Instructions', 'height=300,width=600');
    placeWindow.document.write(instructions);
    const placeButton = placeWindow.document.getElementById('button');
    placeButton.addEventListener('click', () => this.place(placeWindow.document.getElementById('number').value, path));
    const exitButton = placeWindow.document.getElementById('exit');
    exitButton.addEventListener('click', () => this.closePlaceWindow(path, placeWindow));
}

function openTradeWindow(path1, path2) {
    const instructions = '<p>you can move troops from the first country you click to the second one you click.</p><p>you can move up to 3 troops from the first country to the second one.</p><input type="number" id="number" min="1" max="3"><button id="button">trade</button><button id="exit">close</button>';
    const tradeWindow = window.open('', 'Trade Instructions', 'height=300,width=600');
    tradeWindow.document.write(instructions);
    const tradeButton = tradeWindow.document.getElementById('button');
    tradeButton.addEventListener('click', () => this.move(tradeWindow.document.getElementById('number').value, path1, path2));
    const exitButton = tradeWindow.document.getElementById('exit');
    exitButton.addEventListener('click', () => this.closeTradeWindow(path1, path2, tradeWindow));

}

function closeTradeWindow(path1, path2, tradeWindow) {
    tradeWindow.window.close();
    path1.classList.remove("pathFrom");
    path2.classList.remove("pathTo");
    path2.classList.remove("AdjacentPath");
}

function closePlaceWindow(path, placeWindow) {
    placeWindow.window.close();
    path.classList.remove("SelectedPath");
}




function trade(value, path1, path2) {
    let countries = require('../assets/dataStates.json').map.continents.territories;
    let from = countries[path1.attributes.title.value];
    let to = countries[path2.attributes.title.value];
    console.log(from, to, value);

    /*
    if (from != null && from.Armies > value && to != null && value <= 3 && value >= 1 && from.Armies > 1) {
        //to do after with backend
        //console.log(from, to);
        //console.log(from.Armies);
        from.Armies -= value;
        to.Armies += value;
        //console.log("trade successful");
        countries[path1.attributes.title.value].Armies = from.Armies;
        countries[path2.attributes.title.value].Armies = to.Armies;

    }
    else {
        //console.log("trade failed");

    }
    */
}

function changeHoverValue(value) {
    if (value != null && value.target != null) {
        let title = value.target.attributes.title;
        if (title != null) {
            name.value = title.value;
        }
    }
    else {
        name.value = "place your mouse over a country";
    }
}


function setSelectedPath(value) {
    if (value != null && value.target != null && value.target.attributes.title != null) {
        if (value.target.classList.contains("SelectedPath")) {
            value.target.classList.remove("SelectedPath");
            //console.log(value.target.attributes.title, value.target.classList);
            getAdjacentCountries(value);
        }
        else {
            value.target.classList.add("SelectedPath");
            //console.log(value.target.attributes.title, value.target.classList);
            getAdjacentCountries(value);
        }
    }
}



function getAdjacentCountries(value) {
    if (value != null && value.target != null && value.target.attributes.title != null) {
        const country = value.target.attributes.title.value;
        let adjacentCountries = require('../assets/dataStates.json').map.continents.territories[country].neighbours;
        let countryNames = [];
        for (let i = 0; i < adjacentCountries.length; i++) {
            countryNames.push(adjacentCountries[i]);
        }
        this.paths = document.querySelectorAll("path");
        for (let i = 0; i < this.paths.length; i++) {

            if (countryNames.includes(this.paths[i].attributes.title.value) && this.paths[i].classList.contains("AdjacentPath") && !this.paths[i].classList.contains("SelectedPath")) {
                this.paths[i].classList.remove("AdjacentPath");
            }
            else if (countryNames.includes(this.paths[i].attributes.title.value) && !this.paths[i].classList.contains("AdjacentPath")) {
                this.paths[i].classList.add("AdjacentPath");
            }


        }
        ////console.log(countryNames);
    }
}





</script>


<template>
    <div>
        <h1> player : {{ playerName }} </h1>
        <h1> id partita: {{ idMatch }} </h1>
        <h1> current player : {{ currentPlayer }} </h1>
    </div>
    <br>
    <br>
    <div class="container">
        <div class="title">
            <h1>Interactive Risk Map</h1>
        </div>
    </div>
    <div>
        <button @click="startMatch">Start Match</button>
    </div>
    <br>
    <div>
        <button @click="surrend">Bandiera Bianca</button>
    </div>
    <br>
    <br>
    <div>
        <button @click="skip">Skip turn</button>
    </div>
    <br>
    <br>
    <div class="container">
        <h3>
            {{ hoverValue }}
        </h3>
    </div>
    <br>
    <div>
        <p>scegli se posizionare i rinforzi (obbligatorio se non si sono posizionati tutti), attaccare o spostarsi</p>
        <div><input type="radio" id="place" name="action" value="place"><label for="place">Piazza</label></div>
        <div><input type="radio" id="attack" name="action" value="attack"><label for="attack">Attacca</label></div>
        <div><input type="radio" id="move" name="action" value="move"><label for="move">Sposta</label></div>
        <p>inserisci dove o vuoi piazzare o il luogo da cui vuoi muovere o attaccare</p>
        <input type="text" id="start">
        <p>Inserisci nel caso di spostamento o attacco il territorio di destinazione</p>
        <input type="text" id="destination">
        <p>inserisci il numero di truppe da spostare o attaccare o rifornire</p>
        <input type="number" id="number" min="1">
        <button id="doAction">ok</button>
        <button id="exit">close</button>
    </div>
    <br>
    <div>
        <div class="map" v-html="svg"></div>
    </div>
    <br>
    <div>
        <ul>
            <li v-for="continent in continents" :key="continent.name">
                <h3>{{ continent.name }}</h3>
                <div>
            <li v-for="territory in continent.territory" :key="territory.name">
                <h3>Nome territorio: {{ territory.name }}</h3>
                <h3>Armate territorio: {{ territory.army }}</h3>
                <h3>Possessore territorio: {{ territory.owner }}</h3>
            </li>
    </div>
    </li>
    </ul>
    </div>

</template>



<style>
path[class="land Europe"] {

    fill: Purple;
}

path[class="land Asia"] {
    fill: green;
}

path[class="land Africa"] {
    fill: brown;
}

path[class="land North_America"] {
    fill: orange;
}

path[class="land South_America"] {
    fill: blue;
}

path[class="land Oceania"] {
    fill: pink;
}

.pathFrom {
    fill: rgba(128, 128, 128, 0.429);
}


.SelectedPath {
    fill: grey;
}

.adjacentPath {
    fill: #d10154;
}

.pathTo {
    fill: blueviolet;
}

path {
    fill: black;
}

path:hover {
    fill: black;
}
</style>
