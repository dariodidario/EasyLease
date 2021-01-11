function readURL(input) {
    if (input.files && input.files[0]) {
        for(var i=0;i<input.files.length;i++) {
            var reader = new FileReader();
            var img='#img_prev';
            reader.onload = function (e) {
                $(img)
                    .attr('src', e.target.result)
            };

            reader.readAsDataURL(input.files[i]);

        }
    }
}