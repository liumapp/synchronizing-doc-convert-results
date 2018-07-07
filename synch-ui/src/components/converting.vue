/**
 * @file converting.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
<style lang="less">
  .spin-container {
    display: inline-block;
    text-align: center;
    width: 500px;
    height: 200px;
    position: relative;
    border: none;
    .spin-content {
      padding-top: 65px;
    }
  }
</style>
<template>
  <div>
    <Row>
      <Col span="12" offset="6">
        <div class="spin-container">
          <div>
            <!--todo push a table here-->
          </div>
          <div class="spin-content">
            加载完成，点击下载
            <br>
            <Button type="success" @click="downloadPdf">下载</Button>
            <Button type="default" @click="getBack">返回首页</Button>
          </div>
          <!--<Spin size="large" fix v-if="spinShow" ></Spin>-->
        </div>
      </Col>
    </Row>
  </div>

</template>
<script>
import util from '@/libs/util'
import resultColumn from '@/column/resultsColumn'
export default {
  name: 'converting',
  props: [
    'convertId', 'docLists'
  ],
  data () {
    return {
      spinShow: true,
      ws: null,
      filename: '',
      tableColumn: [],
      tableData: [],
    };
  },
  created () {
    this.initTable();
  },
  mounted () {
    if ('window' in window) {
      this.initSocket();
    } else {
      this.$Message.error('your browser not support socket !');
    }
  },
  methods: {
    initTable () {
      this.tableColumn = resultColumn.tableResultsColumn;
      this.tableData = this.docList;
    },
    initSocket () {
      let _vue = this;
      this.ws = new WebSocket(util.convertingResultSocket + "/" + this.convertId);
      this.ws.onmessage = (event) => {
        _vue.getMessage(event);
      };
    },
    getMessage (event) {
      this.filename = event.data;
      this.$Message.success("convert success , the file is : " + event.data);
      this.spinShow = false;
    },
    downloadPdf () {
      window.open(util.ajaxUrl + "download/?filename=" + this.filename);
    },
    getBack () {
      window.location.reload();
    }
  }
}
</script>
