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
    height: 225px;
    position: relative;
    border: none;
  }
</style>
<template>
  <div>
    <Row>
      <Col span="12" offset="6">
        <div class="spin-container">
          <br>
          <div>
            <Table stripe :ref="tableRefs" :columns="tableColumn" :data="tableData"></Table>
          </div>
          <div class="spin-content">
            <br>
            <Button type="default" @click="getBack">返回首页</Button>
          </div>
        </div>
      </Col>
    </Row>
  </div>

</template>
<script>
import status from '@/libs/status'
import util from '@/libs/util'
import resultColumn from '@/column/resultsColumn'
export default {
  name: 'converting',
  props: [
    'convertId', 'docList'
  ],
  data () {
    return {
      ws: null,
      tableRefs: 'convertingResultTable',
      tableColumn: [],
      tableData: []
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
      this.tableColumn.forEach (item => {
        if (item.handle) {
          item.render = (h, params) => {
            const row = params.row;
            const results = [];
            if (row.status == status.CONVERTED_SUCCESS) {
              results.push(this.getDownloadButton(h, row));
            } else {
//           todo 转换失败的情况
              results.push(this.getCancledButton(h, row));
            }
            return h('div', results);
          };
        }
      });
    },
    initSocket () {
      let _vue = this;
      this.ws = new WebSocket(util.convertingResultSocket + "/" + this.convertId);
      this.ws.onmessage = (event) => {
        _vue.getMessage(event);
      };
    },
    /**
     * the event is a json object
     * contains {index, savename, status}
     */
    getMessage (event) {
      let _vue = this;
      let data = event.data;
      data = JSON.parse(data);
      this.tableData.forEach((item,key) => {
        if (key == data.index) {
          if (data.status == status.CONVERTED_SUCCESS) {
            _vue.tableData[key].savename = data.savename;
            _vue.tableData[key].status = status.CONVERTED_SUCCESS;
            this.$Message.success("convert success , the file savename is : " + data.savename);
          } else {
            _vue.tableData[key].savename = data.savename;
            _vue.tableData[key].status = status.CONVERTED_FAILD;
            this.$Message.error("convert failed");
          }
        }
      });
    },
    downloadPdf () {
      window.open(util.ajaxUrl + "download/?filename=" + this.filename);
    },
    getBack () {
      window.location.reload();
    },
    getDownloadButton (h, row) {
      return h('Button', {
        props: {
          type: 'success',
        },
        on: {
          click: () => {
            window.open(util.ajaxUrl + "download/?filename=" + row.savename);
          }
        }
      }, '下载');
    },
    getCancledButton (h, row) {
      let _vue = this;
      return h('Button', {
        props: {
          type: 'warning',
        },
        on: {
          click: () => {
            let index = row._index--;
            _vue.tableData.splice(index, 1);
          }
        }
      }, '取消')
    }
  }
}
</script>
