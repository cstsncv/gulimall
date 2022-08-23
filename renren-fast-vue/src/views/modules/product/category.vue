<template>
    <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
</template>

<script>
export default {
    name: 'RenrenFastVueCategory',

    data() {
        return {
            data: [],
            defaultProps: {
                children: 'children',
                label: 'label'
            }
        };
    },
    methods: {
        handleNodeClick(data) {
            console.log(data);
        }
    },

    methods: {
        getMenus() {
            this.$http({
                url: this.$http.adornUrl('/product/category/list/tree'),
                method: 'get'
            }).then(data=>{
                console.log("成功获取到后端数据,...",data)
            })
        },
    },
    created(){
        this.getMenus()
    }
};
</script>

<style lang="scss" scoped>
</style>