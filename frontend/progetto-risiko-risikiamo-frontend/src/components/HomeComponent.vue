<script>
import axios from 'axios';
import { ref } from 'vue';
export default {

    name: "HomeComponent",
    data() {
        return {
            allData: [],
            messages: [],
            selectedAction: '',
            startLocation: '',
            destination: '',
            armyNumber: 0,
            gameStarted: false,
            idMatch: this.$route.query.id,
            playerName: this.$route.query.name,
            reinforcement: 0,
            svg: null,
            cards: [],
            cardsArr: [],
            continents: [],
            player: [],
            currentPlayer: null,
            players: [],
            hoverValue: name,
            hoverValue2: list,
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
            playerData: [],
            objective: null,
            playerList: [],

        }

    },

    methods: {
        changeHoverValue: changeHoverValue,
        getAllData() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    this.gameStarted = response.data.gameStarted;
                    console.log(this.gameStarted);
                    this.allData = response.data;
                    this.playerList = response.data.players;
                    console.log(this.playerList);
                    for (let i = 0; i < response.data.players.length; i++) {
                        if (response.data.players[i].name == this.playerName) {
                            this.player = response.data.players[i];
                            //console.log(this.player);
                        } else {
                            console.log("error");
                        }
                    }
                    console.log(response.data);
                    this.currentPlayer = response.data.currentPlayer;
                    this.continents = response.data.map.continents;
                    //console.log(this.currentPlayer);
                    if (this.playerName != this.currentPlayer) {
                        console.log("not your turn");
                        //console.log(this.paths);

                    } else {

                        console.log("your turn");
                    }
                    console.log(response.data.winner);
                    this.winner = response.data.winner;
                    
                })
                .catch(error => {
                    console.log(error);
                });

        },
        place(value, path) {
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/place?owner=' + path + '&army=' + value)
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
        getMap() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/svg')
                .then(response => {
                    //console.log(response.data);
                    this.svg = response.data;
                    //console.log(this.svg);
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
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/attack?owner=' + path1 + '&army=' + value + '&neighbor=' + path2)
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                });
        },
        move(value, path1, path2) {
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/move?owner=' + path1 + '&army=' + value + '&neighbor=' + path2)
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                });
        },
        getObjective() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/')
                .then(response => {
                    console.log(response.data.objectives);
                    this.objective = response.data.objectives[0];
                })
                .catch(error => {
                    console.log(error);
                });
        },
        getReinforcement() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/')
                .then(response => {
                    console.log(response.data.renforcements);
                    this.reinforcement = response.data.renforcements;
                })
                .catch(error => {
                    console.log(error);
                });
        },
        cardDiscard(card1, card2, card3) {
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/renforce?c1=' + card1 + '&c2=' + card2 + '&c3=' + card3)
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                });
        },
        submitCards() {
            this.cardDiscard(this.card1, this.card2, this.card3);

        },
        submitForm() {
            if (this.selectedAction === "attack") {
                console.log("attack");
                this.attack(this.armyNumber, this.startLocation, this.destination);
            } else if (this.selectedAction === "move") {
                console.log("move");
                this.move(this.armyNumber, this.startLocation, this.destination);
            } else if (this.selectedAction === "place") {
                console.log("place");
                this.place(this.armyNumber, this.startLocation);
            } else {
                console.log("error");
            }
        },
        getPlayerData() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/')
                .then(response => {
                    console.log(response.data);
                    this.playerData = response.data;
                })
                .catch(error => {
                    console.log(error);
                });

        },
        createCard() {
            let cardsArrPlaceholder = [];
            if (this.playerData !== undefined) {
                for (let i = 0; i < this.cards.length; i++) {
                    let card = {
                        id: this.playerData.name,
                        name: this.cards[i].name,
                        type: this.cards[i].type,
                        color: "background-color:" + this.playerData.color,
                        img: null,
                    }
                    if (card.type === "CANNONE") {
                        card.img = require("../assets/img/Artillery.webp");
                        cardsArrPlaceholder.push(card);
                    } else if (card.type === "CAVALLO") {
                        card.img = require("../assets/img/Cavalry.webp");
                        cardsArrPlaceholder.push(card);
                    } else if (card.type === "FANTE") {
                        card.img = require("../assets/img/Infantry.png");
                        cardsArrPlaceholder.push(card);
                    } else if (card.type === "JOLLY") {
                        card.img = require("../assets/img/Jolly.png");
                    } else {
                        console.log("error");
                    }
                }

                return cardsArrPlaceholder;
            }
        },
    },


    mounted() {
        this.getMap();
        this.getAllData();

    },

    created() {


        setInterval(() => {
            this.getAllData();
            this.getPlayerData();
            console.log(this.playerData);
            this.getReinforcement();
            this.getCards();
            this.createCard();
            this.cardsArr = this.createCard();
        }, 2000);

        setInterval(() => {
            this.getAllData();
            if (this.gameStarted === false) {
                console.log("game not started");

            }
            else {
                console.log("game started");
                this.paths = document.querySelectorAll("path");
                console.log(this.paths);
                for (let i = 0; i < this.paths.length; i++) {
                    this.paths[i].addEventListener("mouseover", this.changeHoverValue);
                }


            }
        }, 3000);

    }
}


const name = ref("place your mouse over a country");
const list = ref([]);



function changeHoverValue(value) {
    if (value != null && value.target != null) {
        let title = value.target.attributes.title;
        let neighbour = findNameTerritory(this.allData.map.continents, title.value);
        console.log(neighbour);
        if (title != null) {
            name.value = title.value;
            list.value = neighbour;
        }
    }
    else {
        name.value = "place your mouse over a country";
        list.value = [];
    }
}

function findNameTerritory(value, name) {

    let continents = value;

    let territory;
    for (let i = 0; i < continents.length; i++) {
        let countries = continents[i].territory;
        for (let j = 0; j < countries.length; j++) {
            if (countries[j].name === name) {
                territory = countries[j];
            }
        }
    }
    console.log(territory.neighbours);
    return territory.neighbours;
}


</script>


<template>
    <div>
        <h1>vincitore : {{ winner }}</h1>
        <h1>obbiettivo : {{ objective }}</h1>
        <h1> player : {{ playerName }} </h1>
        <h1> current reinforcement: {{ reinforcement }} </h1>
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
        <h3>
            {{ hoverValue2 }}
        </h3>
    </div>
    <br>
    <div class="form">
        <p>scegli se posizionare i rinforzi (obbligatorio se non si sono posizionati tutti), attaccare o spostarsi</p>
        <div><input type="radio" id="place" name="action" v-model="selectedAction" value="place"><label
                for="place">Piazza</label></div>
        <div><input type="radio" id="attack" name="action" v-model="selectedAction" value="attack"><label
                for="attack">Attacca</label></div>
        <div><input type="radio" id="move" name="action" v-model="selectedAction" value="move"><label
                for="move">Sposta</label></div>
        <p>inserisci dove o vuoi piazzare o il luogo da cui vuoi muovere o attaccare</p>
        <input type="text" id="start" v-model="startLocation">
        <p>Inserisci nel caso di spostamento o attacco il territorio di destinazione</p>
        <input type="text" id="destination" v-model="destination">
        <p>inserisci il numero di truppe da spostare o attaccare o rifornire</p>
        <input type="number" id="number" min="1" v-model="armyNumber">
        <button id="doAction" @click="submitForm">ok</button>
    </div>
    <br>
    <div>
        <div class="map" v-html="svg"></div>
    </div>
    <br>

    <div class="console2">
        <ul>
            <li v-for="continent in continents" :key="continent.name">
                <h1>{{ continent.name }}</h1>
                <div>
            <li v-for="territory in continent.territory" :key="territory.name">

                <h3 :style="{ color: playerList.find(element => element.name === territory.owner).color }">
                    {{ territory.name }} , {{ territory.army }}</h3>
            </li>

    </div>
    </li>
    </ul>
    </div>

    <br>
    <div class="grid">
        <div class="grid__item" :style="card.color" v-for="card in cardsArr" :key="card">
            <div class="card">
                <img class="card__img"
                    src="../assets/img/RiskLogo.png"
                    alt="UpperImage">
                <div class="card__content">
                    <h1 class="card__header">{{ card.name }}</h1>
                    <p>{{ card.type }}</p>
                    <img class="card__img" :src="card.img" alt="UnderImage">
                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="form2">
        <p>che carte vuoi scartare per privilegi?</p>
        <input type="text" id="card1" v-model="card1">
        <p></p>
        <input type="text" id="card2" v-model="card2">
        <p></p>
        <input type="text" id="card3" v-model="card3">
        <p></p>
        <button id="doAction" @click="submitCards">ok</button>
    </div>

</template>



<style>
.console2 {
    width: 10%;
    height: 200px;
    border: 1px solid black;
    overflow: auto;
    margin-left: 45%;
}

.form {
    width: 15%;
    border: 3px solid black;
    margin-left: 45%;
}

.form2 {
    width: 10%;
    border: 3px solid black;
    margin-left: 45%;
}


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

:root {
    --color-header: #0d0d0d;
    --color-text: #404040;
    --color-btn-text: #3363ff;
    --color-btn-background: #e6ecff;
    --transition: 0.2s;
}

* {
    margin: 0;
    padding: 0;
    box-sizing: inherit;
}

html {
    box-sizing: border-box;
    font-size: 62.5%;
    /* 10px/16px */
}

body {
    display: flex;
    justify-content: center;
    align-content: center;
    padding: 6rem;
    background-color: #f5f5f5;
    font-family: "Inter", sans-serif;
}

@media (max-width: 60em) {
    body {
        padding: 3rem;
    }
}

.grid {
    display: grid;
    width: 5rem;
    grid-gap: 6rem;
    grid-template-columns: repeat(auto-fit, minmax(5rem, 1fr));
    align-items: center;
    margin-left: 30%;
    margin-right: 10%;

}

@media (max-width: 60em) {
    .grid {
        grid-gap: 3rem;
    }
}

.grid__item {
    width: 55%;
    border-radius: .4rem;
    overflow: hidden;
    box-shadow: 0 3rem 6rem rgba(0, 0, 0, .1);
    cursor: pointer;
    transition: var(--transition);
}

.grid__item:hover {
    transform: translateY(-.5%);
    box-shadow: 0 4rem 8rem rgba(0, 0, 0, .2);
}

.card__img {
    display: block;
    width: 100%;
    height: 9rem;
    object-fit: cover;
}

.card__content {
    padding: 3rem 3rem;
}

.card__header {
    font-size: 3rem;
    font-weight: 500;
    color: var(--color-header);
    margin-bottom: 1.5rem;
}

.card__text {
    font-size: 3rem;
    letter-spacing: .1rem;
    line-height: 1.7;
    color: var(--color-text);
    margin-bottom: 2.5rem;
}

.card__btn {
    display: block;
    width: 100%;
    padding: 1.5rem;
    font-size: 2rem;
    text-align: center;
    color: var(--color-btn-text);
    background-color: var(--color-btn-background);
    border: none;
    border-radius: .4rem;
    transition: var(--transition);
    cursor: pointer;
}

.card__btn span {
    margin-left: 1rem;
    transition: var(--transition);
}

.card__btn:hover,
.card__btn:active {
    background-color: var(--color-btn-background-hover);
}

.card__btn:hover span,
.card__btn:active span {
    margin-left: 1.5rem;
}
</style>
