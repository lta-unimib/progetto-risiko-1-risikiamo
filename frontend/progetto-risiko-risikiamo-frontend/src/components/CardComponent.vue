<script>
//import { ref } from 'vue'
//import { cardsList } from '../assets/cards.json';

export default {
    props: ['playerData'],
    name: "cardComponent",
    data() {
        return {
            playerHere: this.playerData,
            cardsArr: [],
            cardsOfPlayer: [],
        }

    },

    methods: {
        // getDataForCards() {
        //     const cardsList = require('../assets/cards.json');
        //     this.cardsPerPlayer = cardsList.players;
        // },
        createCard() {
            let cardsArr = [];
            if (this.playerHere !== undefined) {
                console.log(this.playerHere);
                console.log(this.cardsOfPlayer);
                this.cardsOfPlayer = this.playerHere.cards;
                for (let i = 0; i < this.cardsOfPlayer.length; i++) {
                    let card = {
                        id: this.playerHere.name,
                        name: this.cardsOfPlayer[i].name,
                        type: this.cardsOfPlayer[i].type,
                        color: "background-color:" + this.playerHere.color,
                        img: null,
                    }
                    if (card.type === "CANNONE") {
                        card.img = "https://cdn.pixabay.com/photo/2016/04/01/12/20/armour-1300665__480.png";
                        cardsArr.push(card);
                    } else if (card.type === "CAVALLO") {
                        card.img = "https://static.vecteezy.com/system/resources/thumbnails/000/153/079/small/cavalry-silhouettes-vector.png";
                        cardsArr.push(card);
                    } else if (card.type === "FANTE") {
                        card.img = "https://www.pngall.com/wp-content/uploads/5/Silhouette-Army-Transparent.png";
                        cardsArr.push(card);
                    } else {
                        console.log(card);
                    }
                }

                console.log(cardsArr);
                return cardsArr;
            }
        }
    },

    mounted() {
        //this.getDataForCards();
        this.createCard();
        this.cardsArr = this.createCard();
    }
}



</script>

<template>
    <div class="grid">
        <div class="grid__item" :style="card.color" v-for="card in cardsArr" :key="card">
            <div class="card">
                <img class="card__img"
                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR50AiR1pByChKLMxe1evHAIlcwOJTGzK4yAw&usqp=CAU"
                    alt="UpperImage">
                <div class="card__content">
                    <h1 class="card__header">{{ card.name }}</h1>
                    <p>{{ card.type }}</p>
                    <img class="card__img" :src="card.img" alt="UnderImage">
                    <button class="card__btn">Explore <span>&rarr;</span></button>
                </div>
            </div>
        </div>
    </div>
</template>

<style>
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
    width: 114rem;
    grid-gap: 6rem;
    grid-template-columns: repeat(auto-fit, minmax(30rem, 1fr));
    align-items: start;
}

@media (max-width: 60em) {
    .grid {
        grid-gap: 3rem;
    }
}

.grid__item {
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
    height: 18rem;
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