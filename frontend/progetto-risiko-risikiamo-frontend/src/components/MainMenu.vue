<template>
    <div>
        <h1>Benvenuto a DRISK</h1>
        <h2>Seleziona un svg Map</h2>
        <input type="file" ref="svgFile" @change="readSvgFile" accept=".svg" />
        <div v-if="image">
            <img :src="preview" />
        </div>
        <h2>Seleziona un file json</h2>
        <input type="file" ref="jsonFile" @change="readJsonFile" accept=".json" />
    </div>
</template>
<script>
export default {
    name: "MainMenu",
    data() {
        return {
            svgFile: null,
            jsonFile: null,
            image: false,
            file: false,
            preview: null
        }
    },
    methods: {
        readSvgFile() {
            this.svgFile = this.$refs.svgFile.files[0];
            console.log(this.svgFile);
            if (
                this.svgFile.name.includes(".svg")
            ) {
                this.image = true;
                this.preview = URL.createObjectURL(this.svgFile);
            } else {
                this.image = false;
            }
        },
        readJsonFile() {
            this.jsonFile = this.$refs.jsonFile.files[0];
            console.log(this.jsonFile);
            if (
                this.jsonFile.name.includes(".json")
            ) {
                this.file = true;
            } else {
                this.jsonFile = null;
            }
        }
    }
}
</script>

<style>

</style>

