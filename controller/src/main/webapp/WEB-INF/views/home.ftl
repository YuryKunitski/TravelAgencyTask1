<!DOCTYPE html>
<html>
<head>
    <!-- jQuery Core -->
    <script src="uui/js/lib/jquery-1.12.0.min.js"></script>

    <!-- Bootstrap Core -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="uui/bootstrap/js/bootstrap.min.js"></script>

    <!-- EPAM UUI JavaScript Core -->
    <script src="uui/js/uui-core.min.js" type="text/javascript"></script>

    <!-- EPAM UUI Styles Core -->
    <link rel="stylesheet" href="css/uui-all.css" />
    <!-- Your custom CSS Styles -->
    <link rel="stylesheet" href="css/custom-styles.css" />

    <!-- Scroll for UUI Sidebar -->
    <link rel="stylesheet" href="css/lib/components/jquery.mCustomScrollbar.min.css" />
    <script src="uui/js/lib/components/jquery.mCustomScrollbar.concat.min.js"></script>

    <!-- Include elements that you need described on Getting Started page (above) -->
   <meta charset="utf-8"/>
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
<div class="wrapper">

    <#include "header.ftl">

    <div class="uui-main-container">
        <main>

            <h2>Tour Search</h2>

<!--            <form action="/TravelAgency/search_tours" method="get">-->
<!--                <table>-->
<!--                <tr>-->
<!--                    <th> <@spring.message code="msg.min_cost"/>:<br>-->
<!--                        <input type="number" name="minCost" class="uui-form-element"></th>-->
<!--                    <th> <@spring.message code="msg.min_duration"/>:<br>-->
<!--                        <input type="number" name="minDuration" class="uui-form-element"></th>-->
<!--                    <th> <@spring.message code="msg.min_hotel_stars"/>:<br>-->
<!--                        <input type="number" name="minStars" class="uui-form-element"></th>-->
<!--                    <th> <@spring.message code="msg.start_date"/>:<br>-->
<!--                        <input type="date" name="minDate" class="uui-form-element"></th>-->
<!--                    <th> <@spring.message code="msg.tour_type"/>: <br>-->
<!--                        <select name="tourType" class="selectpicker uui-form-element">-->
<!--                            <option disabled selected><@spring.message code="msg.choose_tour_type"/></option>-->
<!--                            <option value="ECONOM" >econom</option>-->
<!--                            <option value="ALL_INCLUSIVE">all inclusive</option>-->
<!--                            <option value="ONLY_BREAKFAST">only breakfast</option>-->
<!--                        </select>-->
<!--                    </th>-->
<!--                </tr>-->

<!--                    <tr>-->
<!--                        <th> <@spring.message code="msg.max_cost"/>:<br>-->
<!--                            <input type="number" name="maxCost" class="uui-form-element"></th>-->
<!--                        <th> <@spring.message code="msg.max_duration"/>:<br>-->
<!--                            <input type="number" name="maxDuration" class="uui-form-element"></th>-->
<!--                        <th> <@spring.message code="msg.max_hotel_stars"/>:<br>-->
<!--                            <input type="number" name="maxStars" class="uui-form-element"></th>-->
<!--                        <th> <@spring.message code="msg.finish_date"/>:<br>-->
<!--                            <input type="date" name="maxDate" class="uui-form-element"></th>-->
<!--                        <th> <@spring.message code="msg.country"/>: <br>-->
<!--                            <select name="country" class="selectpicker uui-form-element">-->
<!--                                <option disabled selected><@spring.message code="msg.choose_country"/></option>-->
<!--                                  <#list countries as country>-->
<!--                                    <option>${country.name}</option>-->
<!--                                  </#list>-->
<!--                            </select>-->
<!--                        </th>-->
<!--                    </tr>-->
<!--                </table>-->
<!--                <input type="submit" value="Submit">-->
<!--            </form>-->

<!--            ---------------------------------------             -->

            <form action="/TravelAgency/search_tours" method="get">
<!--                <form action=<@spring.url '/TravelAgency/search_tours'/> method="post">-->
                <table>
                    <tr>
<!--                        <th> <@spring.message code="msg.min_cost"/>:<br>-->
<!--                            <input type="number" name="minCost" class="uui-form-element"></th>-->

                        <th><@spring.message code="msg.min_cost"/>:<br>
                            <@spring.formInput "tourDto.minCost" "class='uui-form-element'" "number"/>
                            <br><@spring.showErrors '<br>'/>
                        </th>

                        <th><@spring.message code="msg.min_duration"/>:<br>
                            <@spring.formInput "tourDto.minDuration" "class='uui-form-element'" "number"/>
                            <br><@spring.showErrors '<br>'/>
                        </th>
                        <th><@spring.message code="msg.min_hotel_stars"/>:<br>
                            <@spring.formInput "tourDto.minStars" "class='uui-form-element'" "number"/>
                            <br><@spring.showErrors '<br>'/>
                        </th>
                        <th><@spring.message code="msg.start_date"/>:<br>
                            <@spring.formInput "tourDto.minDate" "class='uui-form-element'" "date"/>
                            <br><@spring.showErrors '<br>'/>
                        </th>
                        <th> <@spring.message code="msg.tour_type"/>: <br>
                            <#assign attributes = "class='selectpicker uui-form-element'"/>
                            <#assign options = { "ECONOM": "econom", "ALL_INCLUSIVE": "all inclusive", "ONLY_BREAKFAST": "only breakfast" } />
                                <@spring.formSingleSelect "tourDto.tourType", options, attributes/>
                        </th>
                    </tr>

                    <tr>
                        <th> <@spring.message code="msg.max_cost"/>:<br>
                            <@spring.formInput "tourDto.maxCost" "class='uui-form-element'" "number"/>
                            <br><@spring.showErrors '<br>'/>
                        </th>
                        <th> <@spring.message code="msg.max_duration"/>:<br>
                            <@spring.formInput "tourDto.maxDuration" "class='uui-form-element'" "number"/>
                            <br><@spring.showErrors '<br>'/>
                        </th>
                        <th> <@spring.message code="msg.max_hotel_stars"/>:<br>
                            <@spring.formInput "tourDto.maxStars" "class='uui-form-element'" "number"/>
                            <br><@spring.showErrors '<br>'/>
                        </th>
                        <th> <@spring.message code="msg.finish_date"/>:<br>
                            <@spring.formInput "tourDto.maxDate" "class='uui-form-element'" "date"/>
                            <br><@spring.showErrors '<br>'/>
                        </th>
                        <th> <@spring.message code="msg.country"/>: <br>
<!--                            <select name="country" class="selectpicker uui-form-element">-->
<!--                                <option disabled selected><@spring.message code="msg.choose_country"/></option>-->
<!--                                <#list countries as country>-->
<!--                                <option>${country.name}</option>-->
<!--                            </#list>-->
<!--                            </select>-->
                            <#assign attributes = "class='selectpicker uui-form-element'"/>
<!--                               <#list countries as country>-->
<!--                                                                </#list>-->
                            <@spring.formSingleSelect "tourDto.countryNames",  "qw", attributes/>
                        </th>
                    </tr>
                </table>
                <input type="submit" value="Submit">
            </form>


            <div>
                <br/>
                <table class="uui-table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Photo</th>
                        <th>Date</th>
                        <th>Duration</th>
                        <th>Description</th>
                        <th>Cost</th>
                        <th>Tour's type</th>
                        <th>Hotel</th>
                        <th>Country</th>
                    </tr>
                    </thead>

                    <#list tours as tour>
                    <tbody>
                    <tr>
                        <td>${tour.id}</td>
                        <td><img src="${tour.photo}" alt=""/></td>
                        <td>${tour.date}</td>
                        <td>${tour.duration}</td>
                        <td>${tour.description}</td>
                        <td>${tour.cost}</td>
                        <td>${tour.tour_type}</td>
                        <td>${tour.hotel_id.name} <br>
                        Stars: ${tour.hotel_id.stars} </td>
                        <td>${tour.country_id.name}</td>
                    </tr>
                    </tbody>
                </#list>

                </table>
            </div>
        </main>

    </div>

    <#include "footer.ftl">

</div>

</body>
</html>