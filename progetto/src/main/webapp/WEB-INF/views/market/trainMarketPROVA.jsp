<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.dao.VotoDAO" %>
<%@ page import="entity.dao.TrenoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import="configuration.JpaConfig" %>
<%@ include file="../navbar.jsp" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Market dei Treni</title>
    <link href="https://fonts.googleapis.com/css2?family=Allerta+Stencil&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        
        h1 {
            color: #8a79c7;
            text-align: center;
            margin: 0;
        }

        .card {
            border: none;
        }
        
        .card-body {
            flex: 1 1 auto;
            padding: 10px;
        }
        
        .card-text {
            font-size: 13px;
        }
        
        .guarantee {
            color: #05882c;
            margin-left: 4px;
            font-weight: 700;
        }
        
        hr {
            margin: 0.2rem 0;
            color: #bfbebe;
        }
        
        .buttons button {
            text-transform: uppercase;
            font-size: 12px;
            border-radius: 2px;
        }
    </style>
</head>
<body>

    <h1 style="margin-top: 2%; font-size: 5.0rem;"><b> Train Market </b></h1>

    <div class="container-fluid mt-2 mb-5">
        <div class="products">
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    
                    <div class="row g-3">

                        <div class="col-md-4">

                            <div class="card"> <img src="https://i.imgur.com/SOMPPzU.jpg" class="card-img-top">
                                <div class="card-body">

                                    <div class="d-flex justify-content-between"> <span class="font-weight-bold">Wood Sofa set-3</span> <span class="font-weight-bold">$550</span> </div>
                                    <p class="card-text mb-1 mt-1">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                    <div class="d-flex align-items-center flex-row"> <img src="https://i.imgur.com/e9VnSng.png" width="20"> <span class="guarantee">2 Years Guarantee</span> </div>
                               
                                </div>
                                <hr>

                                <div class="card-body">

                                    <div class="text-right buttons"> <button class="btn btn-outline-dark">add to wishlist</button> <button class="btn btn-dark">Add to cart</button> </div>
                                </div>
                            </div>

                        </div>
                        
                    </div>
                </div>


               
                
            </div>
        </div>
    </div>



</body>
</html>
