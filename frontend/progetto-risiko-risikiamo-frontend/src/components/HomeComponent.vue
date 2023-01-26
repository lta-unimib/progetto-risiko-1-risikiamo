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
            int1: null,
            int2: null,

        }

    },

    methods: {
        colorSet: colorSet,
        destoyed: function () {
            clearInterval(this.int1);
            clearInterval(this.int2);
        },
        returnError: returnError,
        showMessage: showMessage,
        showWindow: showWindow,
        findNameTerritory: findNameTerritory,
        changeHoverValue: changeHoverValue,
        getAllData() {
           
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    this.gameStarted = response.data.gameStarted;
                    this.allData = response.data;
                    this.playerList = response.data.players;
                    for (let i = 0; i < response.data.players.length; i++) {
                        if (response.data.players[i].name == this.playerName) {
                            this.player = response.data.players[i];
                        
                        }
                    }
                    this.currentPlayer = response.data.currentPlayer;
                    this.continents = response.data.map.continents;  
                    this.winner = response.data.winner;

                }).catch(() => {

                    
                    this.destoyed();
                    this.$router.push({ name: 'main menu', query: { error: "Id or Name not Found" } });
                    

                });
            

        },
        async place(value, path) {
            let response = await axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/place?owner=' + path + '&army=' + value)
                .then(response => {
                    return response.data;

                })
                .catch(error => {
                    return returnError(error);
                });
            return response;
        },
        async startMatch() {
            let response = await axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/start')
                .then(response => {
                   
                    this.gameStarted = true;
                    return response.data;
                })
                .catch(error => {
                    return returnError(error);
                });
            return response;
        },
        getMap() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/svg')
                .then(response => {
                    
                    this.svg = response.data;
                   
                });
        },
        getCards() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/')
                .then(response => {
                    
                    this.cards = response.data.cards;
                });

        },
        async surrend() {
            let response = await axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/surrend')
                .then(() => {
                    
                    this.destoyed();
                    this.$router.push({ name: 'main menu'});
                    return "You have surrended";
                })
                .catch(error => {
                    this.destoyed();
                    this.$router.push({ name: 'main menu' });
                    return returnError(error);
                });
            showMessage(response);
        },
        async skip() {
            let response = await axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/skip')
                .then(() => {
                    
                    return "You have skipped your turn"
                })
                .catch(error => {
                    
                    return returnError(error);
                });
            showMessage(response);
        },
        async attack(value, path1, path2) {
            let response = await axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/attack?owner=' + path1 + '&army=' + value + '&neighbor=' + path2)
                .then(response => {
                  
                    return response.data;
                })
                .catch(error => {
                   
                    return returnError(error);
                });
            return response;
        },
        async move(value, path1, path2) {
            let response = await axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/move?owner=' + path1 + '&army=' + value + '&neighbor=' + path2)
                .then(response => {
                    
                    return response.data;
                })
                .catch(error => {
                 
                    return returnError(error);
                });
            return response;
        },
        getObjective() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/')
                .then(response => {
                   
                    this.objective = response.data.objectives[0];
                });
        },
        getReinforcement() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/')
                .then(response => {
                    
                    this.reinforcement = response.data.renforcements;

                });
        },
        cardDiscard(card1, card2, card3) {
            axios.put('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/renforce?c1=' + card1 + '&c2=' + card2 + '&c3=' + card3)
                .then(() => {
                   
                    showMessage("Nuovi rinforzi");
                })
                .catch(error => {
                   
                    showMessage(returnError(error));
                });
        },
        submitCards() {
            this.cardDiscard(this.card1, this.card2, this.card3);

        },
        async submitForm() {
            if (this.selectedAction === "attack") {
                
                showWindow(this.attack(this.armyNumber, this.startLocation, this.destination));
            } else if (this.selectedAction === "move") {
               
                showWindow(this.move(this.armyNumber, this.startLocation, this.destination));
            } else if (this.selectedAction === "place") {
                
                showWindow(this.place(this.armyNumber, this.startLocation));
            } 
        },
        getPlayerData() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/play/' + this.playerName + '/')
                .then(response => {
                    
                    this.playerData = response.data;
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
                    } 
                }

                return cardsArrPlaceholder;
            }
        },
    },


    

    created() {
        this.int1= setInterval(() => {
            this.getMap();
            this.getObjective();
            this.getAllData();
            this.getPlayerData();
            this.getReinforcement();
            this.getCards();
            this.createCard();
            this.cardsArr = this.createCard();
        }, 2000);

        this.int2 = setInterval(() => {
            this.getAllData();
            if (this.gameStarted !== false) {     
                this.paths = document.querySelectorAll("path");
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

    return territory.neighbours;
}

function showWindow(message) {
    const tradeWindow = window.open('', 'Message', 'height=80,width=300');

    message.then(message => {
        tradeWindow.document.body.innerHTML = message;
        
    });
}
function returnError(error) {
    if (error.response.status != 404)
        return error.response.data;
    else
        return "Territorio non trovato";


}
function showMessage(message) {
    const tradeWindow = window.open('', 'Message', 'height=80,width=300');
    tradeWindow.document.body.innerHTML = message;
 
}

function colorSet(owner)
{
    let color = this.playerList.find(element => element.name === owner) ;
    if(color != null && color != undefined)
    {
        return color.color;
    }
    else
    {
        return "black";
    }
    
}


</script>


<template>
    <div>
        <h1>vincitore : {{ winner }}</h1>
        <h1>obbiettivo : {{ objective }}</h1>

        <h1 :style="{ color: colorSet(playerName) }">
            player : {{ playerName }} </h1>
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
        <button id="start" @click="startMatch">Start Match</button>
    </div>
    <br>
    <div>
        <button id = "surrend" @click="surrend">Bandiera Bianca</button>
    </div>
    <br>
    <br>
    <div>
        <button id = "skip" @click="skip">Skip turn</button>
    </div>
    <br>
    <br>
    
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
        <button id="doAction1" @click="submitForm">ok</button>
    </div>
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

                <h3 :style="{ color: colorSet(territory.owner) }">
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
                <img class="card__img" src="../assets/img/RiskLogo.png" alt="UpperImage">
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
        <button id="doAction2" @click="submitCards">ok</button>
    </div>

</template>



<style>
.console2 {
    width: 20%;
    height: 200px;
    border: 1px solid black;
    overflow: auto;
    margin-left: 40%;
    font-size: 100%;
}

.form {
    width: 15%;
    border: 3px solid black;
    margin-left: 40%;
    font-size: 100%;
}

.form2 {
    width: 10%;
    border: 3px solid black;
    margin-left: 40%;
    font-size: 100%;
}

path[class="land Nord"] {
    fill: green;
}

path[class="land Sud"] {
    fill: red;
}

path[class="land Centro"] {
    fill: yellow;
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
    width: 30%;
    grid-gap: 6rem;
    grid-template-columns: repeat(auto-fit, minmax(50rem, 1fr));
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
