<template>
    <section class="wrapper">
        <div class="top">Welcome to DRISK</div>
        <div class="bottom" aria-hidden="true">Welcome to DRISK</div>
    </section>

    <div>
        <h2>Seleziona un file json</h2>
        <input type="file" ref="jsonFile" @change="onFileChange" accept=".json" />
        <h2>Seleziona nome giocatore</h2>
        <input type="text" v-model="playerName" />
        <h2>Scegli il tuo colore</h2>
        <select v-model="selectedColor">
            <option value=""></option>
            <option value="#B22222">Rosso</option>
            <option value="#DDA0DD">Viola</option>
            <option value="#808080">Nero</option>
            <option value="#32CD32">Verde</option>
            <option value="#1E90FF">Blu</option>
            <option value="#ffffff">Bianco</option>
        </select>
    </div>
    <br>

    <br>
    <br>
    <div>
        <button class="button-30" @click="createGame">Crea Partita</button>
    </div>
    <br>
    <br>
    <div>
        <h2>Partite in corso</h2>
        <ul>
            <li v-bind:id="this.id">{{ id }}</li>
        </ul>
    </div>
    <br>
    <br>
    <div>
        <h2>
            Login
        </h2>
    </div>
    <br>
    <h1 style="color: red">{{ this.$route.query.error }}</h1>
    <br>
    <div>
        <input type="text" v-model="playerName" />
    </div>
    <br>
    <br>
    <div>
        <h2>Scegli il tuo colore</h2>
        <select v-model="selectedColor">
            <option value=""></option>
            <option value="#B22222">Rosso</option>
            <option value="#DDA0DD">Viola</option>
            <option value="#808080">Nero</option>
            <option value="#32CD32">Verde</option>
            <option value="#1E90FF">Blu</option>
            <option value="#ffffff">Bianco</option>
        </select>
    </div>
    <br>
    <br>
    <div>
        <h2>inserisci codice partita</h2>
        <input type="text" v-model="id" />
    </div>
    <br>
    <br>
    <div>
        <button class="button-30" @click="login">Create Player</button>
    </div>
    <br>
    <br>
    <div>


        <form action="/game">

            <input type="text" id="name" name="name" v-model="playerName" hidden />
            <input type="text" id="id" name="id" v-model="id" hidden />
            <input  class="button-30" type="submit" value="LoadGame">
        </form>
       
    </div>
</template>

<script>
import axios from "axios";
//import router from '../routers.js'

export default {
    name: "MainMenu",
    props: ['idMatch'],
    data() {
        return {
            id: null,
            player: null,
            playerName: "",
            selectedColor: "",
            jsonFile: null,
            image: false,
            file: false,
            preview: null
        }
    },
    methods: {
        returnError: returnError,
        showMessage: showMessage,
        onFileChange(e) {
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length) return;
            this.readFile(files[0]);
        },
        readFile(file) {
            let reader = new FileReader();
            reader.onload = e => {
                
                let json = JSON.parse(e.target.result);
                this.jsonFile = json;
            };
            reader.readAsText(file);
        },

        getGame() {
            return this.id;
        },

        createGame() {
            const gameData = this.jsonFile;
            //const player = this.player;
            this.readString(this.playerName, this.selectedColor)
            //console.log(gameData);
            axios.post('http://localhost:3000/api/v1/game/create', gameData)
                .then(response => {
                    axios.post('http://localhost:3000/api/v1/game/' + response.data.id + '/addPlayer', this.player)
                        .then(response2 => {
                            console.log(response2);
                        })
                        .catch(error2 => {
                            showMessage(returnError(error2));
                    
                        });
                    this.id = response.data.id;
                    showMessage("Game created with id: " + this.id);

                })
                .catch(error => {
                    showMessage(returnError(error));
                
                });
        },

       async login() {
            this.readString(this.playerName, this.selectedColor);
            axios.post('http://localhost:3000/api/v1/game/' + this.id + '/addPlayer', this.player)
                .then(response2 => {
                    console.log(response2);
                    showMessage("Player added to game");
                })
                .catch(error2 => {
                    showMessage(returnError(error2));
                });
        },

        readString(value, color) {
            this.playerName = value;
            this.selectedColor = color;
            if (value == "" || value == null) {
                alert("Inserisci un nome valido");
            }
            else {
                let playerPlaceHolder = {
                    name: this.playerName,
                    color: this.selectedColor
                }
                this.player = playerPlaceHolder;
            }

        }
    }


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
    console.log(message);
}
</script>

<style>
* {
    box-sizing: border-box;
}

:root {
    --background-color: black;
    --text-color: hsl(0, 0%, 100%);
}

body {
    margin: 0;
}

.wrapper {
    display: grid;
    place-content: center;
    background-color: var(--background-color);
    min-height: 50vh;
    font-family: "Oswald", sans-serif;
    font-size: clamp(0.7rem, 0.5rem + 9vw, 7.5rem);
    font-weight: 700;
    text-transform: uppercase;
    color: var(--text-color);
}

.wrapper>div {
    grid-area: 1/1/-1/-1;
}

.top {
    clip-path: polygon(0% 0%, 100% 0%, 100% 48%, 0% 58%);
}

.bottom {
    clip-path: polygon(0% 60%, 100% 50%, 100% 100%, 0% 100%);
    color: transparent;
    background: -webkit-linear-gradient(177deg, black 53%, var(--text-color) 65%);
    background: linear-gradient(177deg, black 53%, var(--text-color) 65%);
    background-clip: text;
    -webkit-background-clip: text;
    transform: translateX(-0.02em);
}

.button-30 {
    align-items: center;
    appearance: none;
    background-color: #FCFCFD;
    border-radius: 4px;
    border-width: 0;
    box-shadow: rgba(45, 35, 66, 0.4) 0 2px 4px, rgba(45, 35, 66, 0.3) 0 7px 13px -3px, #D6D6E7 0 -3px 0 inset;
    box-sizing: border-box;
    color: #36395A;
    cursor: pointer;
    display: inline-flex;
    font-family: "JetBrains Mono", monospace;
    height: 48px;
    justify-content: center;
    line-height: 1;
    list-style: none;
    overflow: hidden;
    padding-left: 16px;
    padding-right: 16px;
    position: relative;
    text-align: left;
    text-decoration: none;
    transition: box-shadow .15s, transform .15s;
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
    white-space: nowrap;
    will-change: box-shadow, transform;
    font-size: 18px;
}

.button-30:focus {
    box-shadow: #D6D6E7 0 0 0 1.5px inset, rgba(45, 35, 66, 0.4) 0 2px 4px, rgba(45, 35, 66, 0.3) 0 7px 13px -3px, #D6D6E7 0 -3px 0 inset;
}

.button-30:hover {
    box-shadow: rgba(45, 35, 66, 0.4) 0 4px 8px, rgba(45, 35, 66, 0.3) 0 7px 13px -3px, #D6D6E7 0 -3px 0 inset;
    transform: translateY(-2px);
}

.button-30:active {
    box-shadow: #D6D6E7 0 3px 7px inset;
    transform: translateY(2px);
}

.importantMessage {
    color: red;
    font-size: 20px;
    font-family: "Comic Sans MS", "Comic Sans", cursive;

}
</style>

