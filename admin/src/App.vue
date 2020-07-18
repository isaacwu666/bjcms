<template>
  <div id="app" >

      <transition name="move" mode="out-in">
          <keep-alive :include="tagsList">
              <router-view></router-view>
          </keep-alive>
      </transition>



  </div>
</template>
<style>
  @import "./assets/css/main.css";
  @import "./assets/css/color-dark.css";     /*深色主题*/
  /*@import "./assets/css/theme-green/color-green.css";   浅绿色主题*/
</style>
<script>
    export default {
        data(){
            return{
                tagsList: ['articleList'],
            }
        }
    }
</script>
