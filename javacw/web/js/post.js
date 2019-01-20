/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function deletePost(postId){
    $.ajax({
        url: "posts?postId=" + postId,
        type: 'DELETE',
        success: function (response, status, xhr) {
            if(typeof response === "string") {
                alert(response.replace(/"/g, ''));
                window.location.href = "http://localhost:8080/javacw/posts?filter=all";
            }
        },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}