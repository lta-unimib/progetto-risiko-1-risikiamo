<template>
    <div>
        <input type="text" v-model="idMatch" placeholder="inserisci il codice" />
        <h1></h1>
        <input type="text" v-model="playerName" placeholder="inserisci il tuo nome" />
    </div>
</template>

<script>
//import axios from 'axios';
export default {
    name: "UserComponent",
    data() {
        return {
            playerName: "",
            idMatch: "",
        }
    }
}

</script>

<style>

</style>