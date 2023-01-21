
<template>
    <div>
        <h1>Player Data</h1>
    <form action="/game">
        <label for="fname">First name:</label><br>
        <input type="text" id="name" name="name" value="a"><br>
        <label for="lname">Last name:</label><br>
        <input type="text" id="id" name="id" value="Doe"><br><br>
        <input type="submit" value="Submit">
    </form>

    </div>

</template>

<script>
export default {
    name: 'PlayerData',
    props: {
        jsonData: {
            type: Object,
            default: () => {}
        }
    }
}
</script>