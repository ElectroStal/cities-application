let state = {
    pageState: {
        currentPage: 0,
        pageSize: 10,
        hasNextPage: true,
        hasPreviousPage: false,
        inputTextValue: null,
        itemsList: []
    },
    citiesRequest: {
        uuid: null,
        name: null,
        photo: null
    },
    citiesResponse: {
        hasNextPage: true,
        hasPreviousPage: false,
        name: null,
        photo: null,
        uuid: null,
        citiesList: []
    }
}

export default state;