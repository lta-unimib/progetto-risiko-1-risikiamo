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

}