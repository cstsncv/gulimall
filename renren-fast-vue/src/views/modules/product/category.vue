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
                    <el-button v-if="node.childNodes.length == 0" type="text" size="mini"
                        @click="() => remove(node, data)">
                        Delete
                    </el-button>
                </span>
            </span>
        </el-tree>
        <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">

            <el-form :model="category">
                <el-form-item label="分类名称">
                    <el-input v-model="category.name" autocomplete="off"></el-input>
                </el-form-item>


            </el-form> <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="() => addCategory(category)">确 定</el-button>
            </span>
        </el-dialog>
    </div>

</template>

<script>

export default {
    name: 'RenrenFastVueCategory',

    data() {
        return {
            category: { name: "", parentCid: 0, catLevel: 0, showStatus: 1, sort: 0, productCount: 0 },
            menus: [],
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
            this.category.parentCid = data.catId
            this.category.catLevel = data.catLevel * 1 + 1
            console.log("append: ", data)
        },
        addCategory(category) {
            this.dialogVisible = false
            console.log("添加分类,...", category)
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