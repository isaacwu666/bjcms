<template name="articleList">
    <div class="box">
        <div class="header-list">
            <div style="margin-left: 30px;line-height: 60px"><span>爱旅游爱工作</span></div>

            <div style="margin-left: 50px;width: 80%">
                <el-menu class="el-menu-demo" mode="horizontal" :default-active="activeIndex"  @select="handleSelect">
                  <el-menu-item :index="1" >文章管理</el-menu-item>
                  <el-menu-item :index="3" >类目管理</el-menu-item>
                </el-menu>
            </div>

            <div style="line-height: 60px;margin-right: 30px;float: right"><a href="/" @click="logout()">退出</a></div>

        </div>
        <div class="container-list" v-loading="syncBj">

            <!--                            <div class="article-menu">-->

            <!--                                dfsa-->
            <!--                            </div>-->
            <div style="flex-direction: column;width: 100%">
                <div class="artilce-list">
                    <div style="display: flex; flex-direction: row">


                        <el-form inline style="text-align: center;padding-top: 10px">
                            <el-form-item>
                                <el-button @click="syncBjArticle" v-loading="syncBj">从百家号同步<i class="el-icon-bottom"/>
                                </el-button>
                            </el-form-item>
                            <el-form-item>
                                <el-input label="标题" v-model="baseQuery.title" placeholder="标题"></el-input>
                            </el-form-item>

                            <el-form-item>
                                <el-select v-model="baseQuery.categoryId" filterable clearable placeholder="请选择类目">
                                    <el-option
                                            v-for="category in categoryList"
                                            :key="category.categoryId"
                                            :label="category.categoryName"
                                            :value="category.categoryId">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item style="width: 100px">
                                <el-select v-model="baseQuery.publicStatus" clearable placeholder="发布状态">
                                    <el-option
                                            key="1"
                                            label="已经发布"
                                            value="1">
                                    </el-option>
                                    <el-option
                                            key="0"
                                            label="未经发布"
                                            value="0">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item>
                                <el-button @click="baseQuery.page=1;loadArticleList()"><i
                                        class="el-icon-search"></i>查询
                                </el-button>
                            </el-form-item>
                            <el-form-item>
                                <el-button @click="addArticle()">添加<i
                                        class="el-icon-circle-plus-outline"></i></el-button>
                            </el-form-item>


                        </el-form>


                    </div>
                    <div style="height: 1px; background: #82848a;width: 100%"></div>
                    <el-table @cell-click="articleDetail"
                              v-loading="load"
                              :data="tableData"
                              style="width: 100%">
                        <el-table-column
                                prop="title"
                                label="标题"
                        >
                        </el-table-column>
                        <el-table-column
                                prop="categoryName"
                                label="类目名称"
                                width="180"
                        >

                        </el-table-column>
                        <el-table-column
                                prop="createdOn"
                                label="同步日期"
                                width="180">
                        </el-table-column>

                        <el-table-column
                                fixed="right"
                                label="操作"
                                width="160">
                            <template slot-scope="scope">
                                <!--                                                        <el-button type="text" size="small">站内显示</el-button>-->
                                <el-button type="text" @click="deleteAricle(scope.row)" size="small">删除</el-button>
                            </template>
                        </el-table-column>


                    </el-table>
                </div>
                <div class="block">
                    <el-pagination
                            @size-change="loadArticleList()"
                            @current-change="loadArticleList()"
                            :current-page.sync="baseQuery.page"
                            :page-size="baseQuery.size"
                            layout="total, prev, pager, next"
                            :total="baseQuery.total">
                    </el-pagination>
                </div>
                <!--                <div class="artilce-tips"></div>-->
                <div style="height: 1px;background: #82848a;width: 100%"></div>
            </div>
        </div>
        <div class="footer-list">

            <div style="margin: 0 auto;width: 80%"><span style="width: 100px">@tomweb.xyz</span></div>
        </div>
    </div>
</template>
<style scoped>
    .box {
        /*background: #d9d9d9;*/
        display: -webkit-flex; /* Safari */
        display: flex;
        flex-direction: column; /*s轴为垂直方向，起点在上沿。**/
        overflow-y: scroll;
        height: 100vh; /*// 关键，设置高度为可见高度的100%；*/
    }

    .header-list {
        display: flex;
        flex-direction: row;
        background: #FFFFFF;
        height: 60px;
        box-shadow: 1px 1px 1px 1px #cbcbcb, -1px 1px 1px 1px rgba(255, 255, 255, 0.5)
    }

    .container-list {
        margin: 0 auto;
        width: 90%;
        -webkit-box-flex: 1; /*	//设置1等份占满全屏*/
        min-width: 800px;
        /*background: #FFFFFF;*/
        display: -webkit-flex; /* Safari */
        display: flex;
        flex-direction: row;

    }

    .article-menu {
        width: 150px;
        min-height: 700px;
    }

    .artilce-list {
        margin-top: 10px;
        margin-bottom: 10px;
        width: 100%;
    }

    .artilce-tips {

    }

    .footer-list {
        height: 60px;
        justify-content: center;
        /*width: 100%;*/
        margin: 0 auto;
        line-height: 60px;
    }
</style>
<script>
    export default {
        data() {
            return {
                activeIndex:3,
                load: false,
                syncBj: false,
                tableData: [],
                baseQuery: {
                    publicStatus: null,
                    page: 1,
                    size: 20,
                    total: 0,
                    totalPage: 0,
                    categoryId: null,
                    title: null
                },
                categoryList: []
            }
        },
        mounted() {
            this.loadArticleList();
        },
        created() {
            this.loadAllCategory()
        },
        methods: {
            loadAllCategory() {
                this.categoryLoading = true;
                this.$axios.get("/adminApi/category").then(res => {
                    this.categoryLoading = false;
                    if (res.data.code == "SUCCESS") {
                        this.categoryList = res.data.data;
                        return;
                    }

                }).catch(res => {
                    this.$message.error(res)
                    this.categoryLoading = false;
                })
            },
            logout() {
                localStorage.removeItem('token');
                this.$router.push('/');
            },
            //size-change
            loadArticleList() {
                if (this.load) {
                    return;
                }
                this.load = true;
                this.$axios.get('/adminApi/aritcleList', {params: this.baseQuery}).then((res) => {
                    if (res.data.code == "SUCCESS") {
                        this.tableData = res.data.data;
                        this.baseQuery.total = res.data.total;
                        this.baseQuery.page = res.data.page;
                        this.baseQuery.totalPage = res.data.totalPage;
                        this.load = false;
                    } else {
                        this.load = false;
                        this.$message.error(res.data.msg)
                    }
                }).catch((res) => {
                    this.load = false;
                    console.log(res);
                    this.$message.error(res)
                })
            },
            articleDetail(row, column) {
                // console.log(row, column);

                if (column.label == "操作") {
                    return;
                }
                // console.log(row)
                var id = row.id
                this.$router.push({
                    path: '/articleDetail',
                    query: {
                        id: id
                    }
                })
            },
            addArticle() {
                this.$router.push({
                    path: '/articleDetail',
                    query: {
                        add: true
                    }
                })
                return;
            },
            syncBjArticle() {
                this.syncBj = true
                this.$axios.get("/adminApi/syncBjaritcle").then(res => {
                    this.syncBj = false;
                    if (res.data.code = "SUCCESS") {
                        this.$message.success("同步成功");
                        return;
                    } else {
                        this.$message.error("同步失败");
                        return;
                        ;
                    }
                }).catch(res => {
                    this.$message.error(res)
                    this.syncBj = false;
                })
            },
            //删除文章
            deleteAricle(row) {
                var that = this;
                console.log(row);
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    that.$axios.delete("/adminApi/article/" + row.id).then(res => {
                        if (res.data.code == "SUCCESS") {
                            that.$message.success("删除成功");
                            that.loadArticleList();
                            return;
                        }
                        that.$message.error("删除失败");
                        return;
                    }).catch(res => {
                        that.$message.error("删除失败");
                        return;
                    })


                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });


                this.loadArticleList()
            },
            handleSelect(index){
                console.log(index);
            }
        }
    };
</script>
