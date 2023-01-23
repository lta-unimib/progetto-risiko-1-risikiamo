<script>
import axios from 'axios';
import { ref } from 'vue';
import cardComponent from './CardComponent.vue';
export default {

    name: "HomeComponent",
    components: {
        cardComponent,
    },
    data() {
        return {
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
            playerData: [],
            objective: null,
            playerList: [],
        }

    },

    methods: {
        changeHoverValue: changeHoverValue,
        getPlayerList() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    this.playerList = response.data.players;
                    console.log(this.playerList);
                })
                .catch(error => {
                    console.log(error);
                });
        },
        recoverPlayer() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    for (let i = 0; i < response.data.players.length; i++) {
                        if (response.data.players[i].name == this.playerName) {
                            this.player = response.data.players[i];
                            //console.log(this.player);
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
        setPOV() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    console.log(response.data);
                    this.currentPlayer = response.data.currentPlayer;
                    //console.log(this.currentPlayer);
                })
                .catch(error => {
                    console.log(error);
                });
            if (this.playerName != this.currentPlayer) {
                console.log("not your turn");
                //console.log(this.paths);
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
        getContinents() {
            axios.get('http://localhost:3000/api/v1/game/' + this.idMatch + '/watch')
                .then(response => {
                    this.continents = response.data.map.continents;
                    //console.log(this.continents);
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
    },

    mounted() {
        this.getMap();
    },

    created() {

        // const console = {
        //     log: (...args) => {
        //         this.messages.push(args.join(' '));
        //     }
        // };

        // window.console = console;

        setInterval(() => {
            this.getObjective();
            this.getWinner();
            this.setPOV();
            this.getPlayerList();
            this.getPlayerData();
            console.log(this.playerData);
            this.getContinents();
            this.getReinforcement();
        }, 5000);

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

    }
}


const name = ref("place your mouse over a country");



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
    </div>
    <br>
    <div>
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
    <div class="console">
        <div v-for="(message, index) in messages" :key="index">{{ message }}</div>
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
                <h3 :style="{ color: playerList.find(element => element.name === territory.owner).color }"> Possessore
                    territorio: {{ territory.owner }}</h3>
            </li>
    </div>
    </li>
    </ul>
    </div>

    <br>
    <div>
        <card-component v-if="playerData.length > 0" :playerData="playerData"></card-component>
    </div>

</template>



<style>
.console {
    width: 10%;
    height: 200px;
    border: 1px solid black;
    overflow: auto;
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
</style>
