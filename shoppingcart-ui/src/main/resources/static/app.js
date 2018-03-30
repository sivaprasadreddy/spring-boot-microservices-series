new Vue({
    el: '#app',
    data: {
        products: [],
        cart: {
            customerEmail: '',
            customerAddress: '',
            items: []
        },
        searchOrderId: '',
        order: {},
        orderStatus: ''
    },
    created : function()
    {
        this.fetchProducts();
    },
    methods: {
        fetchProducts: function() {
            $.ajax({
               // url: 'http://localhost:9191/products'
                url: '/ui/api/catalog-service/api/products'
            })
            .done(function(data) {
                this.products = data;
            }.bind(this));
        },
        addToCart: function (product) {
            console.log('addToCart', this.cart);
            var added = false;
            this.cart.items.forEach(function (p) {
                if(p.productId == product.id) {
                    p.quantity = p.quantity +1;
                    added = true;
                    return;
                }
            });
            if(added) return;

            this.cart.items.push({
                productId: product.id,
                quantity: 1,
                productPrice: product.price
            });
            console.log('Cart: ', this.cart);
        },
        placeOrder: function () {
            $.ajax({
                type: 'POST',
                //url: 'http://localhost:9292/orders',
                url: '/ui/api/order-service/api/orders',
                contentType : 'application/json',
                data: JSON.stringify(this.cart)
            })
            .done(function(data) {
                this.cart = {
                    customerEmail: '',
                    customerAddress: '',
                    items: []
                };
                this.orderStatus = "Order created successfully. Order ID:"+data.id

            }.bind(this));
        },
        searchOrder: function () {
            $.ajax({
                type: 'GET',
                //url: 'http://localhost:9292/orders/'+this.searchOrderId,
                url: '/ui/api/order-service/api/orders/'+this.searchOrderId,
                contentType : 'application/json'
            })
            .done(function(data) {
                this.order = data;
            }.bind(this));
        }
    },
    computed: {

    }
});
