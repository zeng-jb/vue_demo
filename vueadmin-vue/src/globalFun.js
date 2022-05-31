
import Vue from 'vue'

Vue.mixin({
    methods: {
        hasAuth(perm) {
            var authority = this.$store.state.menus.permList
            console.log(authority)
            return authority.indexOf(perm) > -1
        }
    }
})