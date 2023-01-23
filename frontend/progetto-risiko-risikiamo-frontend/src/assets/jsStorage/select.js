//nella mount
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

//nella created
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


// functions

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




