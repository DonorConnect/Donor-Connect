<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout title="${model['project'].getName()}" desc="${model['project'].getSummary()}" img="static/${model['project'].getThumbnail()}">

<div class="row">
    <h2 class="project-name">
    ${model["project"].getName()}
    </h2>
 </div>

<div class="row">
    <div class="project">
        <div class="row">
        <div class="projectProgressInfo">
            <table>
                <th>${model["project"].targetAmount}<br><span class="progressTitle">Target Number</span></th>
                <th>${model["donationAmount"]}<br><span class="progressTitle">Pledged</span> </th>
                <th> ${model["project"].leftDays()}<br><span class="progressTitle">Days Left</span></th>
                <th>
                        <progress value="${model["donationPercentage"]}" max="100"></progress>

                    ${model["donationPercentage"]}%

                </th>
            </table>
        </div>
        </div>
        <div class="col8">

            <div class="projectContent">




                <div class="project-image">
                    <img src="static/${model["project"].getImage()}" alt="children">
                </div>

                <!-- AddThis Button BEGIN -->
                <div class="project-share addthis_toolbox addthis_default_style " addthis:title="${model["project"].getName()}">
                    <a class="project-share addthis_button_preferred_1"></a>
                    <a class="project-share addthis_button_preferred_2"></a>
                </div>
                <script type="text/javascript" src="http://s7.addthis.com/js/300/addthis_widget.js#pubid=xa-509a39d35b8e8a39"></script>
                <!-- AddThis Button END -->

                <div class="project-detail">
                    <p> ${model["project"].getDescription()} </p>
                </div>
            </div>
        </div>
        <div class="col4">
            #TODO Contact Information
            <div id="donate-form">
                <#include "donate_form.ftl"/>
            </div>

        </div>
        <div style="clear:both"></div>

    </div>


</div>

</@layout.defaultLayout>