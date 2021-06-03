<template>

<v-data-table
    :headers="headers"
    :items="claimPage"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'ClaimPage',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "id", value: "id" },
        ],
        claimPage : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/claimpages')

      this.claimPage = temp.data._embedded.claimpages;

    },
    methods: {
    }
  }
</script>

