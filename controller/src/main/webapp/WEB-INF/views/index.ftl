<!DOCTYPE html>
<html lang="en">
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

            <div>
                <h2 class="page-header">All tours which provided by a Travel Agency</h2>
            </div>
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
                        <td>${tour.hotel_id.name}</td>
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