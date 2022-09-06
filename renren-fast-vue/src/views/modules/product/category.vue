<template>
    <div>

        <!-- :data 动态属性, 动态绑定data -->
        <el-tree :data="menus" :props="defaultProps" :expand-on-click-node="false" show-checkbox node-key="catId"
            :default-expanded-keys="expandedKeys">
            <span class="custom-tree-node" slot-scope="{ node, data }">
                <span>{{ node.label }}</span>
                <span>
                    <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">
                        Append
                    </el-button>
                    <el-button type="text" size="mini" @click="() => edit(data)">
                        Edit
                    </el-button>
                    <el-button v-if="node.childNodes.length == 0" type="text" size="mini"
                        @click="() => remove(node, data)">
                        Delete
                    </el-button>
                </span>
            </span>
        </el-tree>
        <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="30%">

            <el-form :model="category">
                <el-form-item label="分类名称">
                    <el-input v-model="category.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="图标Icon">
                    <el-input v-model="category.icon" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="计量单位">
                    <el-input v-model="category.productUnit" autocomplete="off"></el-input>
                </el-form-item>


            </el-form> <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="() => submitData(category)">确 定</el-button>
            </span>
        </el-dialog>
    </div>

</template>

<script>

export default {
    name: 'RenrenFastVueCategory',

    data() {
        return {
            category: {
                name: "", parentCid: 0, catLevel: 0, showStatus: 1,
                sort: 0, productCount: 0, catId: null, productUnit: "", icon: ""
            },
            menus: [],
            dialogType: "",
            dialogTitle: "",
            dialogVisible: false,
            expandedKeys: [],
            defaultProps: {
                children: 'children',
                label: 'name'
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
                method: 'get',
                param: this.$http.adornParams()
            }).then(({ data }) => {
                console.log("成功获取到后端数据,...", data.data)
                this.menus = data.data
            })
        },
        // 点击添加
        append(data) {
            this.dialogVisible = true
            this.dialogType = "add"
            this.dialogTitle = "添加分类"
            this.category.parentCid = data.catId
            this.category.catLevel = data.catLevel * 1 + 1
            console.log("append: ", data)
        },
        edit(data) {
            console.log("需要修改的数据: ", data)
            this.dialogType = "edit"
            this.dialogTitle = "修改分类"
            this.dialogVisible = true
            //回显数据, 发送请求获取当前节点最新数据
            this.$http({
                url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
                method: 'get',
                param: this.$http.adornParams()
            }).then(({ data }) => {
                //请求成功
                console.log("要回显的数据: ", data)
                this.category.name = data.data.name
                this.category.catId = data.data.catId
                this.category.icon = data.data.icon
                this.category.productUnit = data.data.productUnit

            })
        },
        submitData(data) {
            if (this.dialogType == "add") {
                this.addCategory(data)
            }
            if (this.dialogType == "edit") {
                this.editCategory(data)
            }
        },
        // 添加输入信息后确定, 修改三级分类
        editCategory(category) {
            console.log("修改分类,...", category)

        },
        // 添加输入信息后确定, 添加三级分类
        addCategory(category) {
            console.log("添加分类,...", category)

            this.$http({
                url: this.$http.adornUrl('/product/category/save'),
                method: 'post',
                data: this.$http.adornData(category, false)
            }).then(
                this.$message({
                    type: 'success',
                    message: `${category.name} 添加成功!`,
                    onClose: () => {
                        //关闭弹出菜单
                        this.dialogVisible = false
                        //刷新新菜单
                        this.getMenus()
                        //设置需要默认展开的node
                        this.expandedKeys = [category.parentCid]
                    }
                })
            ).catch(() => { })
        },

        remove(node, data) {
            var ids = [data.catId]
            this.$confirm(`是否删除当前"${data.name}"菜单?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$http({
                    url: this.$http.adornUrl('/product/category/delete'),
                    method: 'post',
                    data: this.$http.adornData(ids, false)
                }).then(
                    this.$message({
                        type: 'success',
                        message: `${data.name} 删除成功!`,
                        onClose: () => {
                            //刷新新菜单
                            this.getMenus(),
                                //设置需要默认展开的node
                                this.expandedKeys = [node.parent.data.catId]
                        }
                    }),
                    // //设置需要默认展开的node
                    // this.expandedKeys = [node.parent.data.catId],
                    // //刷新新菜单
                    // this.getMenus()

                )
            }).catch(() => { }),
                console.log('删除成功...', node, data, data.name, ids)

        }
    },
    created() {
        this.getMenus()
    },

};
</script>

<style lang="scss" scoped>
</style>