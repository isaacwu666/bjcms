<template name="articleList">
    <div class="box">
        <div class="header-list">
            <span style="line-height: 60px;margin-left: 30px">爱旅游爱工作</span>
            <span style="line-height: 60px;margin-right: 30px;float: right"><a href="/" @click="logout()">退出</a></span>
        </div>
        <div class="container-list">

            <!--                <div class="article-menu"></div>-->
            <div class="artilce-list">

                <el-table @cell-click="articleDetail"
                          :data="tableData"
                          style="width: 100%">
                    <el-table-column
                            prop="title"
                            label="标题"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="createdOn"
                            label="同步日期"
                            width="180">
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
        flex-direction: column;

    }

    .article-menu {

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
                tableData: [],
                baseQuery: {
                    page: 1,
                    size: 20,
                    total: 0,
                    totalPage: 0,
                }
            }
        },
        mounted() {
            this.loadArticleList();
        },
        methods: {
            logout() {
                sessionStorage.removeItem('token');
                this.$router.push('/');
            },
            //size-change
            loadArticleList() {
                this.$axios.get('/adminApi/aritcleList', {params: this.baseQuery}).then((res) => {
                    if (res.data.code == "SUCCESS") {
                        this.tableData = res.data.data;
                        this.baseQuery.total = res.data.total;
                        this.baseQuery.page = res.data.page;
                        this.baseQuery.totalPage = res.data.totalPage;

                    } else {
                        this.$message.error(res.data.msg)
                    }
                }).catch((res) => {
                    console.log(res);
                    this.$message.error(res)
                })
            },
            articleDetail(row) {
                // console.log(row)
                var id=row.id
                this.$router.push({
                    path:'/articleDetail',
                    query:{
                        id:id
                    }
                })
            }
        }
    };
</script>
