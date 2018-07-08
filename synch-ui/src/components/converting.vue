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
      tableRefs: 'convertingResultTable',
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
      this.tableData = resultColumn.tableResultsData;
      this.tableColumn.forEach (item => {
        if (item.handle) {
          item.render = (h, params) => {
            const row = params.row;
            const results = [];
            if (row.status == 1) {
              results.push(this.getDownloadButton(h, row));
            } else {
//           todo 转换失败的情况
              results.push(this.getCancledButton(h, row));
            }
            return h('div', results);
          };
        }
      });
//      this.tableData = this.docList;
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
      },
      getDownloadButton (h, row) {
        return h('Button', {
          props: {
            type: 'success',
          },
          on: {
            click: () => {
              window.open(util.ajaxUrl + "download/?filename=" + row.filename);
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
